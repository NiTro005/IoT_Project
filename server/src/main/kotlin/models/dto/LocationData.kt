package ru.risdeveau.geotracker.models.dto
import kotlinx.serialization.Serializable

@Serializable
data class LocationData(
    val user_name: String,
    val lon: Double,
    val lat: Double
)
