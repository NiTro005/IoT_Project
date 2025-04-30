package ru.risdeveau.geotracker.routers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.testRouts(){
    routing {
        get("/") {
            call.respond(HttpStatusCode.OK, "Kirill konchenyi mazohist")
        }
    }
}