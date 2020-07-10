package com.tomgozdek.railstationdistance.repository

import com.tomgozdek.railstationdistance.database.Station

interface Repository {
    suspend fun reloadData()

    suspend fun getStation(id : Long) : Station

    suspend fun getAllStations() : List<Station>

    suspend fun getStationKeywords()

    suspend fun findMatchinStations(pattern : String) : List<Station>
}