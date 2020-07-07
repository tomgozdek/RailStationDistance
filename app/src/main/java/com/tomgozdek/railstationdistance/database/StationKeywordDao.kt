package com.tomgozdek.railstationdistance.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StationKeywordDao {
    @Insert
    fun insertAll(vararg keywords: StationKeyword)

    @Query("SELECT * FROM station_keywords_table")
    fun getAll() : List<StationKeyword>
}