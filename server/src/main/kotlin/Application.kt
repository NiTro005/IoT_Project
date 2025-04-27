package ru.risdeveau.geotracker

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import ru.risdeveau.geotracker.Routers.LoginRoutings;
import ru.risdeveau.geotracker.Routers.module;

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    val host = "0.0.0.0"

    embeddedServer(Netty, port, host){
        module();
        LoginRoutings();
    }.start(wait = true)
}