package ru.risdeveau.geotracker.controllers

import io.ktor.websocket.*
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import ru.risdeveau.geotracker.models.dto.LocationData
import java.time.LocalDate
import ru.risdeveau.geotracker.models.repositories.LocationRepos.getAllUsersInOneDay

class WebSocketController {
    suspend fun webSocketController(
        incoming: ReceiveChannel<Frame>,
        outgoing: SendChannel<Frame>
    ) {
        try {
            println("New WebSocket connection established")
            while (true) {
                val today = LocalDate.now().toString()
                val data = getAllUsersInOneDay(today)

                outgoing.send(Frame.Text(Json.encodeToString(data)))
                println("Sent ${data.size} records")

                delay(15000)
            }
        } catch (e: Exception) {
            when (e) {
                is ClosedSendChannelException ->
                    println("Client disconnected normally")
                else ->
                    println("Error: ${e.javaClass.simpleName} - ${e.message}")
            }
        } finally {
            outgoing.close()
        }
    }
}
