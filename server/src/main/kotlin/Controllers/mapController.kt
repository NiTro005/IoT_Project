package ru.risdeveau.geotracker.Controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import ru.risdeveau.geotracker.data.frontData

class mapController {
    suspend fun mapPost(call: ApplicationCall){
        val frontData = frontData("kikita", 23.3, 11.4);//TODO("Получние данных по нику пользователя из БД")
        //call.respond(HttpStatusCode.OK, frontData);
    }

    suspend fun mapGet(call: ApplicationCall){

    }
}
