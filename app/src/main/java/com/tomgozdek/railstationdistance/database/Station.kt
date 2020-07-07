package com.tomgozdek.railstationdistance.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations_table")
data class Station(
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "latitude")
    val latitude : Float,
    @ColumnInfo(name = "longitude")
    val longitude : Float,
    @ColumnInfo(name = "hits")
    val hits : Int,
    @ColumnInfo(name = "city")
    val city : String
)