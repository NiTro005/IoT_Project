package ru.risdeveau.geotracker.Controllers

import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.server.application.*
import ru.risdeveau.geotracker.data_base.Supabase

class DBController {
    suspend fun connectDB(call: ApplicationCall){
        try {
            val result = Supabase.client.postgrest["locations"]
                .select(count = Count.EXACT)
                .decodeSingle<Map<String, Int>>()

            call.respond(
                mapOf(
                    "status" to "OK",
                    "message" to "Database connection successful",
                    "count" to (result["count"] ?: 0)
                ),
                typeInfo = TODO()
            )
        } catch (e: Exception) {
            call.respond(
                mapOf(
                    "status" to "error",
                    "message" to "Database connection failed: ${e.message}"
                ), typeInfo = TODO()
            )
        }
    }
}