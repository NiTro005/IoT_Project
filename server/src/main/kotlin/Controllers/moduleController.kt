package ru.risdeveau.geotracker.Controllers

import io.ktor.server.application.*
import io.ktor.server.response.*

class moduleController {

    suspend fun working(call: ApplicationCall) {
        call.respondText("Hello from GeoTracker API!")
    }

    suspend fun apiStatus(call: ApplicationCall) {
        call.respond(/*mapOf("status" to "OK", "version" to "0.0.1")*/ "Ok")
    }

    suspend fun getMap(call: ApplicationCall){

    }

    suspend fun postMap(call: ApplicationCall){

    }
}