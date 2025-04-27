package ru.risdeveau.geotracker.Routers

import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

import ru.risdeveau.geotracker.Controllers.moduleController;
import ru.risdeveau.geotracker.data_base.Supabase

fun Application.module() {
    val moduleController = moduleController();
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/",){
            moduleController.working(call);
        }

        get("/api/status") {
            moduleController.apiStatus(call);
        }

        get("/:login/main", ) {}

        get("/test_connect_db") {
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
}