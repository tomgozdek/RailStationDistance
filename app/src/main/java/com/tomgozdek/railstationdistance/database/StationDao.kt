package com.tomgozdek.railstationdistance.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StationDao {
    @Insert
    fun insertAll(vararg stations : Station)

    @Query("SELECT * FROM stations_table WHERE id LIKE :stationId")
    fun findById(stationId : Int) : Station
}