package ru.risdeveau.geotracker.routers

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.risdeveau.geotracker.controllers.mapController
import ru.risdeveau.geotracker.controllers.WebSocketController

fun Application.mapRouts() {
    val mapController = mapController();
    val webSocketController = WebSocketController()

    routing {
        webSocketController.setupWebSocketRoutes(this)
        get("/map") {
            mapController.mapGet(call);
        }

    }
}