package ru.risdeveau.geotracker.config

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


object Supabase {
    private const val SUPABASE_URL = "https://rumvwrachgjguyqejuhs.supabase.co"
    private val SUPABASE_KEY = System.getenv("SUPABASE_KEY")

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_KEY
        ) { install(Postgrest) }
    }
}
