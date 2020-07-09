package com.tomgozdek.railstationdistance.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.tomgozdek.railstationdistance.R
import com.tomgozdek.railstationdistance.database.Station

@BindingAdapter("android:text")
fun setText(view: TextView, station : Station?){
    if (station != null) {
        view.text = view.resources.getString(R.string.station_label, station.name, station.city)
    } else {
        view.text = view.resources.getText(R.string.add_station_prompt)
    }
}