package ru.risdeveau.geotracker.routers

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.risdeveau.geotracker.controllers.mapController

fun Application.mapRouts() {
    val mapController = mapController();

    routing {

        get("/map") {
            mapController.mapGet(call);
        }

        post("/map"){
            mapController.mapPost(call);
        }
    }
}