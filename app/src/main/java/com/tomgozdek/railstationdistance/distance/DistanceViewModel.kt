package com.tomgozdek.railstationdistance.distance

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tomgozdek.railstationdistance.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DistanceViewModel(private val repository : Repository) : ViewModel(){

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        Log.d("DistanceViewModel", "Init, reload data")
        viewModelScope.launch {
            repository.reloadData()
        }
    }
}