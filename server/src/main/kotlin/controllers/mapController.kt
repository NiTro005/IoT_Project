package ru.risdeveau.geotracker.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.risdeveau.geotracker.models.dto.LocationData

class mapController {
    suspend fun mapPost(call: ApplicationCall){
        val frontData = LocationData("kikita", 23.3, 11.4);//TODO("Получние данных по нику пользователя из БД")
        call.respond(HttpStatusCode.OK, frontData)
    }

    suspend fun mapGet(call: ApplicationCall){

    }
}
