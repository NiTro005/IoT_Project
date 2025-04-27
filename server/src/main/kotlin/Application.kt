package ru.risdeveau.geotracker

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.risdeveau.geotracker.Routers.module
import ru.risdeveau.geotracker.Routers.DBRoutes

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    val host = "0.0.0.0"

    embeddedServer(Netty, port, host){
        module();
        DBRoutes();
    }.start(wait = true)
}

