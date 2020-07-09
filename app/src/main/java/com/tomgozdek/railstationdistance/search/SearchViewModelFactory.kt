package com.tomgozdek.railstationdistance.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tomgozdek.railstationdistance.repository.Repository

class SearchViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Invalid viewmodel class")
        }
    }

}