package ru.risdeveau.geotracker.data_base
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class LocationData(
    val user_name: String,
    val lon: Double,
    val lat: Double
)
