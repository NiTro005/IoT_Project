package ru.risdeveau.geotracker.routers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import ru.risdeveau.geotracker.models.dto.LocationData
import ru.risdeveau.geotracker.models.repositories.LocationRepos

fun Application.testRouts(){
    routing {
        get("/") {
            call.respond(HttpStatusCode.OK, "Kirill konchenyi mazohist and petuh")
        }
        get("test_db_clean") {
            try {
                LocationRepos.insert(LocationData("Oleg", 0.0, 0.0))
                LocationRepos.insert(LocationData("Artem", 0.0, 0.0))
                LocationRepos.insert(LocationData("Nikita", 0.0, 0.0))
                LocationRepos.insert(LocationData("Gleb", 0.0, 0.0))
                LocationRepos.insert(LocationData("Kto-to pyatyi", 0.0, 0.0))
                call.respond(mapOf("status" to "ok", "action" to "clean old data and insert new"))
            } catch (e: Exception) {
                call.respond(e.message.toString())
            }

        }

        get("allUsers") {
            try {
                val locations = LocationRepos.getAllUsersInOneDay("2025-05-01")
                call.respond(locations)
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    "Ошибка при получении данных: ${e.message}"
                )
            }
        }
        get("getUser") {
            try {
                val locations = LocationRepos.getUserInOneDay("2025-05-01", "Oleg")
                call.respond(locations)
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    "Ошибка при получении данных: ${e.message}"
                )
            }
        }
    }
}