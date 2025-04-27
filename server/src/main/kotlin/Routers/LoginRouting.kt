package ru.risdeveau.geotracker.Routers

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.LoginRoutings(){
    routing {
        get("/login"){}

        get("/registration"){}
    }
}