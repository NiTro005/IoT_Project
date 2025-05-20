package ru.risdeveau.geotracker.routers

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.risdeveau.geotracker.controllers.appController

fun Application.appRouts() {
    val appController = appController();

    routing {

        post("/api/app"){
            appController.appUserPost(call);
        }

    }
}