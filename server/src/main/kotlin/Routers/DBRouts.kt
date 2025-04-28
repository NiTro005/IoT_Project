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
        get("/test_connect_db") {
            DBController.connectDB(call);
        }
        get ("/test_insert_in_DB") {
            val data = LocationData("Kirill", 12.0, 45.0)
            val result = DBController.insert(data)
            if (result.isSuccess) {
                call.respond(
                    HttpStatusCode.Created,
                    ApiResponse(
                        status = "Create",
                        message = "Insert successful",
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.ExpectationFailed,
                    ApiResponse(
                        status = "ERROR",
                        message = "Insert failure",
                    )
                )
            }
        }
    }
}