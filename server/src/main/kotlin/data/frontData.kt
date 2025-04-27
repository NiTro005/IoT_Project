package ru.risdeveau.geotracker.data
import kotlinx.serialization.Serializable

@Serializable
data class frontData(
    val nik:String,
    val x : Double,
    val y: Double )