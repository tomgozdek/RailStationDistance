package com.tomgozdek.railstationdistance.repository

import com.tomgozdek.railstationdistance.database.StationDatabase
import com.tomgozdek.railstationdistance.network.KoleoApi
import com.tomgozdek.railstationdistance.network.toDatabaseModel

class StationsRepository(private val database: StationDatabase, private val koleaoApi : KoleoApi){
    suspend fun reloadData(){
        val stations = koleaoApi.service.getStations()
        database.stationDao().insertAll(stations.toDatabaseModel())
        val stationKeywords = koleaoApi.service.getStationKeywords()
        database.stationKeywordDao().insertAll(stationKeywords.toDatabaseModel())
    }
}