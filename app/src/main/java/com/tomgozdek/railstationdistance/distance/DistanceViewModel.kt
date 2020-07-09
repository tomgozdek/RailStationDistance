package com.tomgozdek.railstationdistance.distance

import androidx.lifecycle.*
import com.tomgozdek.railstationdistance.database.Station
import com.tomgozdek.railstationdistance.repository.Repository
import com.tomgozdek.railstationdistance.util.Event
import kotlinx.coroutines.*
import java.io.IOException

class DistanceViewModel(private val repository : Repository) : ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _onSearchStationRequested = MutableLiveData<Event<Unit>>()
    val onSearchStationRequested : LiveData<Event<Unit>>
        get() = _onSearchStationRequested

    private var startStation = MutableLiveData<Station>()
    private var destinationStation = MutableLiveData<Station>()
    private var selectedStation = MutableLiveData<Station>()

    private val stationDistanceCalculator = MediatorLiveData<Float>().apply {
        addSource(startStation,  Observer { calculateDistance(it, destinationStation.value) })
        addSource(destinationStation, Observer { calculateDistance(startStation.value, it) })
    }

    private fun calculateDistance(startStation: Station?, destinationStation: Station?){
        if(startStation != null && destinationStation != null){
            stationDistanceCalculator.value = calculateDistance(
                startStation.latitude,
                startStation.longitude,
                destinationStation.latitude,
                destinationStation.longitude
            )
        }
    }

    val distanceString = Transformations.map(stationDistanceCalculator){
        it.div(1000).toString()
    }

    init {
        viewModelScope.launch {
            try {
                repository.reloadData()
            } catch (exception : IOException){
                //TODO handle no network
            }
        }
    }


    fun onStartStationSearchClick() {
        _onSearchStationRequested.value = Event(Unit)
        selectedStation = startStation
    }

    fun onDestinationStationSearchClick() {
        _onSearchStationRequested.value = Event(Unit)
        selectedStation = destinationStation
    }

    fun onSearchResult(stationId : Int){
        setStation(stationId)
    }

    private fun setStation(stationId: Int){
        viewModelScope.launch {
            selectedStation.value = getStation(stationId)
        }
    }

    private suspend fun getStation(stationId: Int) : Station{
        return withContext(Dispatchers.IO){
            repository.getStation(stationId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}