package ru.risdeveau.geotracker.routers

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import ru.risdeveau.geotracker.controllers.mapController
import ru.risdeveau.geotracker.controllers.WebSocketController

fun Application.mapRouts() {
    val mapController = mapController();
    val webSocketController = WebSocketController()

    routing {

        webSocket("/map/ws"){
            webSocketController.webSocetControlleer(incoming, outgoing)
        }
        get("/map") {
            mapController.mapGet(call);
        }

    }
}