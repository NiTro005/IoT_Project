package ru.risdeveau.geotracker.routers

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.risdeveau.geotracker.controllers.DBController

fun Application.DBRoutes() {
    val DBController = DBController();
    routing {
        get("/health") {
            DBController.healthCheck(call);
        }

    }
}