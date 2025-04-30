package ru.risdeveau.geotracker.models.repositories

import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.runBlocking
import ru.risdeveau.geotracker.config.Supabase
import ru.risdeveau.geotracker.models.dto.LocationData
object LocationRepos {
    private const val CLEAN_PERIOD = 5

    init {
        runBlocking { clean() }
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

    private suspend fun clean(){
        TODO()
    }
}