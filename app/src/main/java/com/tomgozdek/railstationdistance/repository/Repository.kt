package com.tomgozdek.railstationdistance.repository

import com.tomgozdek.railstationdistance.database.Station

interface Repository {
    suspend fun reloadData()

    suspend fun getStation(id : Int) : Station

    fun getStationKeywords()
}