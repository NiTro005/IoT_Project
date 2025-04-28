package ru.risdeveau.geotracker.Controllers

import Supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

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
}