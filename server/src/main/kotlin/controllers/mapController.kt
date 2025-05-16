package ru.risdeveau.geotracker.controllers

import io.ktor.server.application.*
import io.ktor.server.response.*
import java.io.File


class mapController {

    suspend fun mapGet(call: ApplicationCall){
        val filePath = "static/index.html"
        val file = File(this::class.java.classLoader.getResource(filePath)!!.toURI())
        call.respondFile(file)
    }
}
