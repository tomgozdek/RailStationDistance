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

    private val _startStation = MutableLiveData<Station>()
    val startStation : LiveData<Station>
        get() = _startStation

    private var _destinationStation = MutableLiveData<Station>()
    val destinationStation : LiveData<Station>
        get() = _destinationStation

    private var selectedStation = MutableLiveData<Station>()

    private val stationDistanceCalculator = MediatorLiveData<Float>().apply {
        addSource(_startStation,  Observer { calculateDistance(it, _destinationStation.value) })
        addSource(_destinationStation, Observer { calculateDistance(_startStation.value, it) })
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

    val distanceCalculated = Transformations.map(stationDistanceCalculator){
        it != null
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
        selectedStation = _startStation
    }

    fun onDestinationStationSearchClick() {
        _onSearchStationRequested.value = Event(Unit)
        selectedStation = _destinationStation
    }

    fun onSearchResult(stationId : Long){
        setStation(stationId)
    }

    private fun setStation(stationId: Long){
        viewModelScope.launch {
            selectedStation.value = getStation(stationId)
        }
    }

    private suspend fun getStation(stationId: Long) : Station{
        return withContext(Dispatchers.IO){
            repository.getStation(stationId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}