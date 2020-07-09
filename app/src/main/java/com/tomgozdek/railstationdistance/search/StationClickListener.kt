package com.tomgozdek.railstationdistance.search

import com.tomgozdek.railstationdistance.database.Station

class StationClickListener(val clickListener: (stationId : Long) -> Unit){
    fun onClick(station : Station) = clickListener(station.id)
}