package ru.risdeveau.geotracker.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.risdeveau.geotracker.models.dto.LocationData
import java.io.File
import java.time.LocalDate

import ru.risdeveau.geotracker.models.repositories.LocationRepos.getAllUsersInOneDay
import ru.risdeveau.geotracker.models.repositories.LocationRepos.getUserInOneDay

class mapController {
    suspend fun mapPost(call: ApplicationCall){
        val today: String = LocalDate.now().toString();
        val data : List<LocationData> = getAllUsersInOneDay(today)
        call.respond(HttpStatusCode.OK, data)
    }

    suspend fun mapGet(call: ApplicationCall){
    suspend fun mapUserPost (call: ApplicationCall) {
        val user_name = call.parameters["user_name"].toString()
        val today: String = LocalDate.now().toString();
        val data : List<LocationData> = getUserInOneDay(today, user_name)
        call.respond(HttpStatusCode.OK, data)
    }

    }
}
