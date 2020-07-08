package com.tomgozdek.railstationdistance.repository

interface Repository {
    suspend fun reloadData()

    fun getStationKeywords()
}