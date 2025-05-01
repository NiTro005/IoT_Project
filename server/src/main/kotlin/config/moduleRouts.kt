package ru.risdeveau.geotracker.config

import io.ktor.server.application.*
import ru.risdeveau.geotracker.routers.DBRoutes
import ru.risdeveau.geotracker.routers.mapRouts
import ru.risdeveau.geotracker.routers.testRouts

fun Application.module() {
    configureSurealization()
    DBRoutes()
    mapRouts()
    testRouts()
}