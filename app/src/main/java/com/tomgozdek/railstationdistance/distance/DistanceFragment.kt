package com.tomgozdek.railstationdistance.distance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tomgozdek.railstationdistance.R
import com.tomgozdek.railstationdistance.database.StationDatabase
import com.tomgozdek.railstationdistance.databinding.FragmentDistanceBinding
import com.tomgozdek.railstationdistance.network.KoleoApi
import com.tomgozdek.railstationdistance.repository.StationsRepository
import com.tomgozdek.railstationdistance.search.SearchFragment
import com.tomgozdek.railstationdistance.util.EventObserver

class DistanceFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentDistanceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_distance, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModelFactory = DistanceViewModelFactory(StationsRepository(StationDatabase.getInstance(requireContext()), KoleoApi))
        val viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(DistanceViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.onSearchStationRequested.observe(viewLifecycleOwner, EventObserver {
            navigateToSearchFragment()
        })

        setFragmentResultListener(SearchFragment.SEARCH_RESULT_KEY) { _, bundle ->
            val result = bundle.getInt(SearchFragment.STATION_ID)
            viewModel.onSearchResult(result)
        }

        return binding.root
    }

    private fun navigateToSearchFragment() {
        findNavController().navigate(DistanceFragmentDirections.actionDistanceFragmentToSearchFragment())
    }

}