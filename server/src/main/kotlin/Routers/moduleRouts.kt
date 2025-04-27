package ru.risdeveau.geotracker.Routers

import io.ktor.server.application.*
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

        get("/:login/main", ) {}
    }
}