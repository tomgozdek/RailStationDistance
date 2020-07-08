package com.tomgozdek.railstationdistance.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Station(
    val id : Long,
    val name : String,
    @Json(name = "name_slug")
    val nameSlug : String,
    val _latitude : Float?,
    val _longitude : Float?,
    val hits : Int,
    val _ibnr : Int?,
    val city : String,
    val region : String,
    val country : String,
    @Json(name = "localised_name")
    @NullToEmptyString
    val localisedName : String = ""
){
    val latitude = _latitude ?: 0F
    val longitude = _longitude ?: 0F
    val ibnr = _ibnr ?: 0
}

fun List<Station>.toDatabaseModel() : List<com.tomgozdek.railstationdistance.database.Station> {
    return this.map {
        com.tomgozdek.railstationdistance.database.Station (
            id = it.id,
            name = it.name,
            latitude = it.latitude,
            longitude = it.longitude,
            city = it.city,
            hits = it.hits
        )
    }
}