package com.tomgozdek.railstationdistance.repository

import com.tomgozdek.railstationdistance.database.Station
import com.tomgozdek.railstationdistance.database.StationDatabase
import com.tomgozdek.railstationdistance.network.KoleoApi
import com.tomgozdek.railstationdistance.network.toDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StationsRepository(private val database: StationDatabase, private val koleaoApi : KoleoApi) : Repository{
    override suspend fun reloadData(){
        withContext(Dispatchers.IO){
            val stations = koleaoApi.service.getStations()
            database.stationDao().insertAll(stations.toDatabaseModel())
            val stationKeywords = koleaoApi.service.getStationKeywords()
            database.stationKeywordDao().insertAll(stationKeywords.toDatabaseModel())
        }
    }

    override fun getStationKeywords() {
        database.stationKeywordDao().getAll()
    }

    override suspend fun getStation(id: Int): Station = database.stationDao().findById(id)
}