package ru.risdeveau.geotracker.routers

import io.ktor.server.application.*
import ru.risdeveau.geotracker.config.Supabase
import ru.risdeveau.geotracker.config.configureSurealization

fun Application.module() {
    configureSurealization()
    DBRoutes()
    mapRouts()
}