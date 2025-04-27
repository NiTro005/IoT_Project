package ru.risdeveau.geotracker

import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.risdeveau.geotracker.data_base.LocationData
import ru.risdeveau.geotracker.data_base.Supabase

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    val host = "0.0.0.0"

    embeddedServer(
        factory = Netty,
        port = port,
        host = host,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/") {
            call.respondText("Server is running")
        }

        get("/check-db-connection") {
            try {
                val result = Supabase.client.postgrest["locations"]
                    .select(count = Count.EXACT)
                    .decodeSingle<Map<String, Int>>()

                call.respond(mapOf(
                    "status" to "OK",
                    "message" to "Database connection successful",
                    "count" to (result["count"] ?: 0)
                ))
            } catch (e: Exception) {
                call.respond(mapOf(
                    "status" to "error",
                    "message" to "Database connection failed: ${e.message}"
                ))
            }
        }
    }
}