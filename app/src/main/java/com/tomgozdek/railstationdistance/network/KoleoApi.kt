package com.tomgozdek.railstationdistance.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object KoleoApi {
    private const val BASE_URL = "https://koleo.pl/api/v2/main/"

    val service : KoleoService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .baseUrl(BASE_URL)
            .build()
            .create(KoleoService::class.java)
    }

    private fun getMoshi() = Moshi.Builder()
        .add(NullToEmptyStringAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()
}