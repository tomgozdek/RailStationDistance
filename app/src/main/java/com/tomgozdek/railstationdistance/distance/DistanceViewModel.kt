package com.tomgozdek.railstationdistance.distance

import androidx.lifecycle.ViewModel
import com.tomgozdek.railstationdistance.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class DistanceViewModel(private val repository : Repository) : ViewModel(){

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        viewModelScope.launch {
            try {
                repository.reloadData()
            } catch (exception : IOException){
                //TODO handle no network
            }
        }
    }
}