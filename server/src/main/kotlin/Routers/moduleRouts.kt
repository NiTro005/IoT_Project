package ru.risdeveau.geotracker.Routers

import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

import ru.risdeveau.geotracker.Controllers.moduleController;

fun Application.module() {
    val moduleController = moduleController();

    routing {
        get("/",){
            moduleController.working(call);
        }

        get("/api/status") {
            moduleController.apiStatus(call);
        }

    }
}