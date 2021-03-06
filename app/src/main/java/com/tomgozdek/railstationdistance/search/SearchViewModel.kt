package com.tomgozdek.railstationdistance.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomgozdek.railstationdistance.database.Station
import com.tomgozdek.railstationdistance.repository.Repository
import com.tomgozdek.railstationdistance.util.Event
import kotlinx.coroutines.*
import java.io.IOException

class SearchViewModel(private val repository: Repository) : ViewModel(){

    private val _searchInput = MutableLiveData<String>()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    repository.reloadData()
                } catch (exception : IOException){
                    val stations = repository.getAllStations()
                    if(stations.isEmpty()){
                        _databaseEmptyEvent.postValue(Event(Unit))
                    }
                }
            }
        }
    }

    private val _databaseEmptyEvent = MutableLiveData<Event<Unit>>()
    val databaseEmptyEvent : LiveData<Event<Unit>>
        get() = _databaseEmptyEvent

    private val _searchResult = MutableLiveData<List<Station>>()
    val searchResult : LiveData<List<Station>>
        get() = _searchResult

    fun onSearchInputChange(sequence: CharSequence, start : Int, before : Int, count : Int){
        _searchInput.value = sequence.toString()

        if(sequence.toString().length >= 2){
            viewModelScope.launch {
                _searchResult.value = searchStations(sequence.toString())
            }
        } else {
            _searchResult.value = emptyList()
        }
    }

    private suspend fun searchStations(pattern : String) : List<Station>{
        return withContext(Dispatchers.IO){
            repository.findMatchinStations("%$pattern%")
        }
    }
}