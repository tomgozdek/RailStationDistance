package com.tomgozdek.railstationdistance.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.tomgozdek.railstationdistance.R
import com.tomgozdek.railstationdistance.database.Station
import com.tomgozdek.railstationdistance.databinding.FragmentSearchBinding
import com.tomgozdek.railstationdistance.distance.DistanceFragmentDirections

class SearchFragment : Fragment(){

    companion object {
        const val SEARCH_RESULT_KEY = "search_result"
        const val STATION_ID = "station_id"
    }

    private lateinit var binding : FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.button.setOnClickListener{
            val action = SearchFragmentDirections.actionSearchFragmentToDistanceFragment()
            setFragmentResult(SEARCH_RESULT_KEY, bundleOf(STATION_ID to 12343))
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}