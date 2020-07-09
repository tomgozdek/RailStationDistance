package com.tomgozdek.railstationdistance.distance

import android.location.Location

fun calculateDistance(
    startLatitude : Double,
    startLongitude : Double,
    endLatitude : Double,
    endLongitude : Double) : Float {
    var result = FloatArray(3)
    Location.distanceBetween(startLatitude,startLongitude,endLatitude,endLongitude, result)
    return result[0]
}