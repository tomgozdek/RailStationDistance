package com.tomgozdek.railstationdistance.distance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tomgozdek.railstationdistance.R
import com.tomgozdek.railstationdistance.database.StationDatabase
import com.tomgozdek.railstationdistance.databinding.FragmentDistanceBinding
import com.tomgozdek.railstationdistance.network.KoleoApi
import com.tomgozdek.railstationdistance.repository.StationsRepository

class DistanceFragment : Fragment(){

    private lateinit var binding : FragmentDistanceBinding
    private lateinit var viewModel: DistanceViewModel
    private lateinit var viewModelFactory: DistanceViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_distance, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModelFactory = DistanceViewModelFactory(StationsRepository(StationDatabase.getInstance(requireContext()), KoleoApi))
        viewModel = viewModelFactory.create(DistanceViewModel::class.java)

        binding.viewModel = viewModel



        return binding.root
    }
}