package com.tomgozdek.railstationdistance.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations : List<Station>)

    @Query("SELECT * FROM stations_table WHERE id LIKE :stationId")
    fun findById(stationId : Int) : Station

    @Query("SELECT * FROM stations_table WHERE name LIKE :pattern ORDER BY hits DESC LIMIT 10")
    fun findMatchingStations(pattern : String) : List<Station>
}