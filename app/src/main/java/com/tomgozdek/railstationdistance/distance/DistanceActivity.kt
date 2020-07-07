package com.tomgozdek.railstationdistance.distance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tomgozdek.railstationdistance.R
import com.tomgozdek.railstationdistance.databinding.ActivityMainBinding

class DistanceActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}