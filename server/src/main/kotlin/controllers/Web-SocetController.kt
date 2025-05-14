package ru.risdeveau.geotracker.controllers

import io.ktor.server.routing.*
import  io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.json.Json
import ru.risdeveau.geotracker.models.dto.LocationData
import java.time.LocalDate
import ru.risdeveau.geotracker.models.repositories.LocationRepos.getAllUsersInOneDay

class WebSocketController {
    fun setupWebSocketRoutes(routing: Routing) {
        routing.webSocket("/map/ws") {
            for (frame in incoming) {
                val today: String = LocalDate.now().toString();
                val data : List<LocationData> = getAllUsersInOneDay(today)
                val resp = Json.encodeToString(data);
                outgoing.send(Frame.Text(resp))
            }
        }
    }
}
