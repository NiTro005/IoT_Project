package ru.risdeveau.geotracker.Controllers

import Supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import ru.risdeveau.geotracker.data_base.LocationData

@Serializable
data class ApiResponse(
    val status: String,
    val message: String,
    val count: Long? = null,
    val details: String? = null
)

class DBController {
    suspend fun connectDB(call: ApplicationCall) {
        try {
            val result = Supabase.client.from("locations").select{ count(Count.EXACT) }.countOrNull()
            call.respond(
                HttpStatusCode.OK,
                ApiResponse(
                    status = "OK",
                    message = "Database connection successful",
                    count = result
                )
            )
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.ServiceUnavailable,
                ApiResponse(
                    status = "error",
                    message = "Database connection failed",
                    details = e.message
                )
            )
        }
    }

    suspend fun insert(data: LocationData): Result<Unit> {
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
}
