package com.tomgozdek.railstationdistance.network

import com.squareup.moshi.Json

data class StationKeyword(
    val id : Long,
    val keyword : String,
    @Json(name = "station_id")
    val stationId : Long
)