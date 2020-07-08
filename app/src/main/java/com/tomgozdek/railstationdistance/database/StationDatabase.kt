package com.tomgozdek.railstationdistance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Station::class, StationKeyword::class], version = 1, exportSchema = false)
abstract class StationDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE : StationDatabase? = null

        fun getInstance(context: Context) : StationDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StationDatabase::class.java,
                        "stations_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }

    abstract fun stationDao() : StationDao

    abstract fun stationKeywordDao() : StationKeywordDao

}