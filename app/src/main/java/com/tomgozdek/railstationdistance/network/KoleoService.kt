package com.tomgozdek.railstationdistance.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface KoleoService {
    companion object  {
        const val BASE_URL : String = "https://koleo.pl/api/v2/main/"
    }

    @Headers("X-KOLEO-Version: 1")
    @GET("stations")
    fun getStations() : Call<List<Station>>

    @Headers("X-KOLEO-Version: 1")
    @GET("station_keywords")
    fun getStationKeywords() : Call<List<StationKeyword>>
}


