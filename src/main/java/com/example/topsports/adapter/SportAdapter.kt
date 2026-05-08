package com.example.topsports.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.topsports.R
import com.example.topsports.data.remote.model.SportItem
import com.example.topsports.databinding.ItemSportBinding

class SportAdapter(
    private val onItemClick: (SportItem) -> Unit
) : ListAdapter<SportItem, SportAdapter.SportViewHolder>(DiffCallback()) {

    inner class SportViewHolder(private val binding: ItemSportBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sport: SportItem) {
            binding.tvSportName.text = sport.strSport
            binding.tvSportDescription.text =
                sport.strSportDescription?.take(80)?.plus("...") ?: "Sport popular la nivel mondial"

            // Încarcă imaginea cu Glide
            Glide.with(binding.root.context)
                .load(sport.strSportThumb)
                .placeholder(R.drawable.ic_sport_placeholder)
                .error(R.drawable.ic_sport_placeholder)
                .centerCrop()
                .into(binding.ivSportImage)

            binding.root.setOnClickListener {
                onItemClick(sport)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val binding = ItemSportBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<SportItem>() {
        override fun areItemsTheSame(oldItem: SportItem, newItem: SportItem) =
            oldItem.idSport == newItem.idSport

        override fun areContentsTheSame(oldItem: SportItem, newItem: SportItem) =
            oldItem == newItem
    }
}
