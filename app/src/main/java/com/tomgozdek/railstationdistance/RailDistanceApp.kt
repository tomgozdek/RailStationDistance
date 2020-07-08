package com.tomgozdek.railstationdistance

import android.app.Application
import androidx.work.*
import com.tomgozdek.railstationdistance.worker.FetchWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class RailDistanceApp : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        scheduleFetchWorker()
    }

    private fun scheduleFetchWorker() {
        applicationScope.launch {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val request = PeriodicWorkRequestBuilder<FetchWorker>(24, TimeUnit.HOURS)
                .setConstraints(constraints)
                .setBackoffCriteria(
                    BackoffPolicy.EXPONENTIAL,
                    1,
                    TimeUnit.HOURS
                )
                .build()

            WorkManager.getInstance().enqueueUniquePeriodicWork(
                FetchWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request)
        }
    }
}

