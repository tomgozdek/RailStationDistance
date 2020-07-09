package com.tomgozdek.railstationdistance.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomgozdek.railstationdistance.database.Station
import com.tomgozdek.railstationdistance.repository.Repository
import kotlinx.coroutines.*

class SearchViewModel(private val repository: Repository) : ViewModel(){

    private val _searchInput = MutableLiveData<String>()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _searchResult = MutableLiveData<List<Station>>()
    val searchResult : LiveData<List<Station>>
        get() = _searchResult

    fun onSearchInputChange(sequence: CharSequence, start : Int, before : Int, count : Int){
        _searchInput.value = sequence.toString()

        if(sequence.toString().length > 2){
            viewModelScope.launch {
                _searchResult.value = searchStations(sequence.toString())
            }
        }
    }

    private suspend fun searchStations(pattern : String) : List<Station>{
        return withContext(Dispatchers.IO){
            repository.findMatchinStations("%$pattern%")
        }
    }
}