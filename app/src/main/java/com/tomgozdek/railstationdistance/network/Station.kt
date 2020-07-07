package com.tomgozdek.railstationdistance.network

import com.squareup.moshi.Json

data class Station(
    val id : Long,
    val name : String,
    @Json(name = "name_slug")
    val nameSlug : String,
    val latitude : Float,
    val longitude : Float,
    val hits : Int,
    val ibnr : Int,
    val city : String,
    val region : String,
    val country : String,
    @Json(name = "localised_name")
    @NullToEmptyString
    val localisedName : String = ""
)