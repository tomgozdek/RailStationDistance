package com.tomgozdek.railstationdistance.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Station::class, StationKeyword::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun stationDao() : StationDao

    abstract fun stationKeywordDao() : StationKeywordDao

}