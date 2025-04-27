package ru.risdeveau.geotracker.Routers

import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import ru.risdeveau.geotracker.data_base.Supabase
import ru.risdeveau.geotracker.Controllers.DBController

fun Application.DBRoutes() {
    install(ContentNegotiation) {
        json()
    }
    val DBController = DBController();
    routing {
        get("/test_connect_db") {
            DBController.connectDB(call);
        }
    }
}