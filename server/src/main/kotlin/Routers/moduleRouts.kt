package ru.risdeveau.geotracker.Routers

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

import ru.risdeveau.geotracker.Controllers.moduleController;

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
    }
}