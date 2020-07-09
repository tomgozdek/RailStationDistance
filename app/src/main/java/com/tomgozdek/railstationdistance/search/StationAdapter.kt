package com.tomgozdek.railstationdistance.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tomgozdek.railstationdistance.database.Station
import com.tomgozdek.railstationdistance.databinding.SearchListItemBinding

class StationAdapter(private val clickListener: StationClickListener) : ListAdapter<Station, StationAdapter.ViewHolder>(StationDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = getItem(position)
        holder.bind(station, clickListener)
    }

    class ViewHolder private constructor(val binding: SearchListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(station: Station, clickListener: StationClickListener) {
            binding.station = station
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchListItemBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }
    }
}

class StationDiffCallback : DiffUtil.ItemCallback<Station>(){
    override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean = oldItem == newItem
}