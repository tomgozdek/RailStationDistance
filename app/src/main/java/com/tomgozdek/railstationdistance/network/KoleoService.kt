package com.tomgozdek.railstationdistance.network

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface KoleoService {
    companion object  {
        const val BASE_URL : String = "https://koleo.pl/api/v2/main/"
    }

    @Headers("X-KOLEO-Version: 1")
    @GET("stations")
    suspend fun getStations() : List<Station>

    @Headers("X-KOLEO-Version: 1")
    @GET("station_keywords")
    suspend fun getStationKeywords() : List<StationKeyword>
}


