package ru.risdeveau.geotracker

import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import ru.risdeveau.geotracker.Routers.module
import ru.risdeveau.geotracker.Routers.DBRoutes
import ru.risdeveau.geotracker.Surealization.configureSurealization

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    val host = "0.0.0.0"

    embeddedServer(Netty, port, host){
        module();
        DBRoutes();
        configureSurealization();
    }.start(wait = true)
}

