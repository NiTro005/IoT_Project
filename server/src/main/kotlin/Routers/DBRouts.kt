package ru.risdeveau.geotracker.Routers

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.risdeveau.geotracker.Controllers.ApiResponse
import ru.risdeveau.geotracker.Controllers.DBController
import ru.risdeveau.geotracker.data_base.LocationData

fun Application.DBRoutes() {
    val DBController = DBController();
    routing {
        get("/health") {
            DBController.healthCheck(call);
        }
    }
}