package ru.risdeveau.geotracker.Routers

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.risdeveau.geotracker.Controllers.mapController

fun Application.mapRout() {
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