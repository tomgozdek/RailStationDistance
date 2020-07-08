package com.tomgozdek.railstationdistance.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StationKeyword(
    val id : Long,
    val keyword : String,
    @Json(name = "station_id")
    val stationId : Long
)

fun List<StationKeyword>.toDatabaseModel() : List<com.tomgozdek.railstationdistance.database.StationKeyword> {
    return this.map {
        com.tomgozdek.railstationdistance.database.StationKeyword(
            id = it.id,
            keyword = it.keyword,
            stationId = it.stationId
        )
    }
}