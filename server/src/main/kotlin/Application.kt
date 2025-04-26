package ru.risdeveau.geotracker

import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
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
            call.respondText("Hello from GeoTracker API!")
        }

        get("/test-db-connection") {
            try {
                val count = Supabase.client.postgrest["locations"]
                    .select(count = Count.EXACT) {
                        eq("user_name", "test-user")
                    }
                    .decodeSingle<Map<String, Int>>()

                call.respond(mapOf(
                    "status" to "OK",
                    "message" to "Connection to Supabase successful",
                    "test_records_count" to count["count"]
                ))
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.ServiceUnavailable,
                    mapOf(
                        "error" to "DB connection failed",
                        "details" to e.message
                    )
                )
            }
        }

        post("/test-db-insert") {
            val testData = LocationData(
                user_name = "test-user",
                lon = 0.0,
                lat = 0.0
            )

            try {
                Supabase.client.postgrest["locations"].insert(testData)
                call.respond(mapOf("status" to "OK", "message" to "Test data inserted"))
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    mapOf("error" to "Insert failed: ${e.message}")
                )
            }
        }
    }
}