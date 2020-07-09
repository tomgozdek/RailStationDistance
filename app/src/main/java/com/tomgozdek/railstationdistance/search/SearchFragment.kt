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
import com.tomgozdek.railstationdistance.databinding.FragmentSearchBinding

class SearchFragment : Fragment(){

    companion object {
        const val SEARCH_RESULT_KEY = "search_result"
        const val STATION_ID = "station_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        binding.button.setOnClickListener{
            setFragmentResult(SEARCH_RESULT_KEY, bundleOf(STATION_ID to 802))
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDistanceFragment())
        }

        return binding.root
    }

}