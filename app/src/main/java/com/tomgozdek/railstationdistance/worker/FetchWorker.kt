package com.tomgozdek.railstationdistance.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.tomgozdek.railstationdistance.database.StationDatabase
import com.tomgozdek.railstationdistance.network.KoleoApi
import com.tomgozdek.railstationdistance.repository.StationsRepository
import java.io.IOException

class FetchWorker(applicationContext: Context, params: WorkerParameters) :
    CoroutineWorker(applicationContext, params) {

    companion object {
        const val WORK_NAME = "com.tomgozdek.railstationdistance.worker.FetchWorker"
    }

    override suspend fun doWork(): Result {
        try {
            val repository = StationsRepository(StationDatabase.getInstance(applicationContext), KoleoApi)
            repository.reloadData()
        } catch (exception : IOException){
            return Result.retry()
        }
        return Result.success()
    }

}