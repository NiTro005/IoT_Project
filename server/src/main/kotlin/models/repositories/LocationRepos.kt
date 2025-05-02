package ru.risdeveau.geotracker.models.repositories

import io.github.jan.supabase.auth.PostgrestFilterDSL
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Count
import io.github.jan.supabase.postgrest.query.filter.PostgrestFilterBuilder
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.*
import ru.risdeveau.geotracker.config.Supabase
import ru.risdeveau.geotracker.models.dto.LocationData

object LocationRepos {
    private const val CLEAN_PERIOD = 5

    init {
        runBlocking { cleanOldData() }
    }

    suspend fun insert(data:LocationData): Result<Unit>{
        if (data.lat !in -90.0..90.0 || data.lon !in -180.0..180.0) {
            return Result.failure(IllegalArgumentException("Invalid coordinates. Lat must be between -90 and 90, Lon between -180 and 180"))
        }
        return try {
            Supabase.client.from("locations").insert(data)
            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    private suspend fun cleanOldData() {
        try {
            val cutoffDate = Clock.System.now()
                .minus(CLEAN_PERIOD.toLong(), DateTimeUnit.DAY, TimeZone.UTC)
                .toString()
            Supabase.client.from("locations").delete {
                filter {
                    lt("created_at", cutoffDate)
                }
            }
        } catch (e: Exception){
            println("Failed to clean old locations: ${e.message}")
        }
    }
    private suspend fun getLocationsFilter(
        day:String,
        additionalFilter: PostgrestFilterBuilder.()->Unit = { }
    ): List<LocationData>{
        val dayStart = Instant.parse("${day}T00:00:00Z")
        val dayEnd = dayStart.plus(1, DateTimeUnit.DAY, TimeZone.UTC)
        return try {
            Supabase.client.from("locations").select {
                filter {
                    gt("created_at", dayStart.toString())
                    lt("created_at", dayEnd.toString())
                    this.additionalFilter()
                }
            }.decodeList<LocationData>()
        } catch (e: Exception){
            emptyList<LocationData>()
        }
    }

    suspend fun getAllUsersInOneDay(day:String):List<LocationData>{
        return getLocationsFilter(day)
    }

    suspend fun getUserInOneDay(day:String, user:String): List<LocationData> {
        return getLocationsFilter(day) {eq("user_name", user)}
    }

    suspend fun countOfRows(): Long? =
        Supabase.client.from("locations").select{ count(Count.EXACT) }.countOrNull()
}