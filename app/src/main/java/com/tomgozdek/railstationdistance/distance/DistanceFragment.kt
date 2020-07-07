package com.tomgozdek.railstationdistance.distance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tomgozdek.railstationdistance.R
import com.tomgozdek.railstationdistance.databinding.FragmentDistanceBinding

class DistanceFragment : Fragment(){

    private lateinit var binding : FragmentDistanceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_distance, container, false)

        return binding.root
    }
}