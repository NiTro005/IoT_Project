package ru.risdeveau.geotracker.data_base
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.server.response.*
import org.slf4j.LoggerFactory

object Supabase {
    private val log = LoggerFactory.getLogger("SupabaseConfig")
    private const val SUPABASE_URL = "https://rumvwrachgjguyqejuhs.supabase.co"
    private val SUPABASE_KEY = System.getenv("SUPABASE_KEY") ?: "default"

    init {
        log.info("Initializing Supabase with key prefix: ${SUPABASE_KEY.take(7)}")
    }
    val client = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) { install(Postgrest) }
}