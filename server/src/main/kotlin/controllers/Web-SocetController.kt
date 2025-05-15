package ru.risdeveau.geotracker.controllers

import io.ktor.websocket.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.serialization.json.Json
import ru.risdeveau.geotracker.models.dto.LocationData
import java.time.LocalDate
import ru.risdeveau.geotracker.models.repositories.LocationRepos.getAllUsersInOneDay

class WebSocketController {
    suspend fun webSocetControlleer(incoming: ReceiveChannel<Frame>, outgoing: SendChannel<Frame>) {
            for (frame in incoming) {
                val today: String = LocalDate.now().toString();
                val data : List<LocationData> = getAllUsersInOneDay(today)
                val resp = Json.encodeToString(data);
                outgoing.send(Frame.Text(resp))
            }
    }
}
