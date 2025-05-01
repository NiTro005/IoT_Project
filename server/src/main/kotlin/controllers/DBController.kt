package ru.risdeveau.geotracker.controllers

import ru.risdeveau.geotracker.config.Supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Count
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*
import ru.risdeveau.geotracker.models.dto.LocationData
import ru.risdeveau.geotracker.models.repositories.LocationRepos

class DBController {
    suspend fun healthCheck(call: ApplicationCall) {
        try {
            val result = LocationRepos.countOfRows()
            call.respond(HttpStatusCode.OK, mapOf
                ("status" to "ok", "message" to "Data base connect successful", "count" to result.toString()))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.ServiceUnavailable)
        }
    }
}
