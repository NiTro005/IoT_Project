package ru.risdeveau.geotracker.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

import ru.risdeveau.geotracker.models.dto.LocationData
import ru.risdeveau.geotracker.models.repositories.LocationRepos

class appController {
    suspend fun appUserPost(call: ApplicationCall){
        val user = call.receive<LocationData>();
        LocationRepos.insert(user);
        println("Added to DB");
        call.respond(HttpStatusCode.OK)
    }
}