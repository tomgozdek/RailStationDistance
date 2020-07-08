package com.tomgozdek.railstationdistance.distance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tomgozdek.railstationdistance.repository.Repository

class DistanceViewModelFactory(val repository : Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DistanceViewModel::class.java)){
            return DistanceViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Invalid viewmodel class")
        }
    }

}