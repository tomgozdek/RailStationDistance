package com.tomgozdek.railstationdistance.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KoleoApiTest {

    @Test
    fun getStations_request_returns_not_null_not_empty_List()= runBlocking{
        val response : List<Station> = KoleoApi.service.getStations()
        assertThat(response, `is`(notNullValue()))
        assertThat(response.isEmpty(), `is`(false))
    }

    @Test
    fun getStationKeywords_request_returnsNonNullList()  = runBlocking{
        val response : List<StationKeyword> = KoleoApi.service.getStationKeywords()
        assertThat(response, `is`(notNullValue()))
        assertThat(response.isEmpty(), `is`(false))
    }
}