package com.tomgozdek.railstationdistance.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StationKeywordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(keywords: List<StationKeyword>)

    @Query("SELECT * FROM station_keywords_table")
    fun getAll() : List<StationKeyword>
}