package ru.risdeveau.geotracker.models.repositories

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Count
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.*
import ru.risdeveau.geotracker.config.Supabase
import ru.risdeveau.geotracker.models.dto.LocationData
import java.time.temporal.ChronoUnit

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
    suspend fun getAllUsersInOneDay(day:String): List<LocationData>{
        TODO()
    }
    suspend fun getUserInOneDay(day:String): List<LocationData> {
        TODO()
    }
    suspend fun countOfRows(): Long? =
        Supabase.client.from("locations").select{ count(Count.EXACT) }.countOrNull()
}