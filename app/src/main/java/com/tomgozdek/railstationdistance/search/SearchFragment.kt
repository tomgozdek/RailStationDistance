package com.tomgozdek.railstationdistance.search

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tomgozdek.railstationdistance.R
import com.tomgozdek.railstationdistance.database.StationDatabase
import com.tomgozdek.railstationdistance.databinding.FragmentSearchBinding
import com.tomgozdek.railstationdistance.network.KoleoApi
import com.tomgozdek.railstationdistance.repository.StationsRepository
import com.tomgozdek.railstationdistance.util.EventObserver

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
        val viewModelFactory = SearchViewModelFactory(StationsRepository(StationDatabase.getInstance(requireContext()), KoleoApi))
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        val stationAdapter = StationAdapter(StationClickListener { stationId ->
            setFragmentResult(SEARCH_RESULT_KEY, bundleOf(STATION_ID to stationId))
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDistanceFragment())
        })

        binding.searchList.adapter = stationAdapter

        viewModel.searchResult.observe(viewLifecycleOwner, Observer {stations ->
            stationAdapter.submitList(stations)
        })

        viewModel.databaseEmptyEvent.observe(viewLifecycleOwner, EventObserver {
            showDatabaseEmptyDialog()
        })

        return binding.root
    }

    private fun showDatabaseEmptyDialog() {
        val dialog = AlertDialog.Builder(requireActivity())
                                .setMessage(R.string.offline_dlg_msg)
                                .setTitle(R.string.offline_dlg_title)
            .setPositiveButton(R.string.btn_ok) {_, _ ->
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDistanceFragment())
            }.create()
        dialog.show()
    }

}