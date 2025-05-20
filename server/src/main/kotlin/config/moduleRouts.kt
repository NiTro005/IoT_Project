package ru.risdeveau.geotracker.config

import io.ktor.server.application.*
import io.ktor.server.websocket.*
import ru.risdeveau.geotracker.routers.DBRoutes
import ru.risdeveau.geotracker.routers.mapRouts
import ru.risdeveau.geotracker.routers.testRouts
import ru.risdeveau.geotracker.routers.appRouts
import kotlin.time.Duration.Companion.seconds

fun Application.module() {
    install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    configureSurealization()
    DBRoutes()
    mapRouts()
    testRouts()
    appRouts()
}