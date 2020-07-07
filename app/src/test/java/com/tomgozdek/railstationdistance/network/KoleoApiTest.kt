package com.tomgozdek.railstationdistance.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class KoleoApiTest {

    @Test
    fun getStations_request_returnsNonNullList(){
        val response : Response<List<Station>> = KoleoApi.service.getStations().execute()
        assertThat(response.isSuccessful, `is`(true))
        assertThat(response.body(), `is`(notNullValue()))
    }

    @Test
    fun getStationKeywords_request_returnsNonNullList(){
        val response : Response<List<StationKeyword>> = KoleoApi.service.getStationKeywords().execute()
        assertThat(response.isSuccessful, `is`(true))
        assertThat(response.body(), `is`(notNullValue()))
    }
}