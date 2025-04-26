package ru.risdeveau.geotracker.data_base
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object Supabase {
    private const val SUPABASE_URL = "https://rumvwrachgjguyqejuhs.supabase.co"
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJ1bXZ3cmFjaGdqZ3V5cWVqdWhzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NTI1NjUxMiwiZXhwIjoyMDYwODMyNTEyfQ.9SXfmpisYqbHXClmk82wnFho59UWsutNS_s4FQi1SrI"

    val client = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {install(Postgrest)}
}