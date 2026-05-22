package com.example.banknoteproject.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.banknoteproject.data.domain.entities.Feature
import com.example.banknoteproject.databinding.ItemFeatureBinding

class FeatureAdapter : ListAdapter<Feature, FeatureAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemFeatureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Feature) {
            binding.tvFeatureTitle.text = item.title.trim()
            binding.tvFeatureValue.text = item.value.trim()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFeatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Feature>() {
        override fun areItemsTheSame(oldItem: Feature, newItem: Feature): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Feature, newItem: Feature): Boolean {
            return oldItem == newItem
        }
    }
}
