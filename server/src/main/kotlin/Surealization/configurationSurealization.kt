package ru.risdeveau.geotracker.Surealization

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSurealization() {
    install(ContentNegotiation) {
        json()
    }
}