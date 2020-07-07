package com.tomgozdek.railstationdistance.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "station_keywords_table")
data class StationKeyword(
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    @ColumnInfo(name = "keyword")
    val keyword : String,
    @ColumnInfo(name = "station_id")
    val stationId : Long)