package com.example.banknoteproject.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banknoteproject.R
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.databinding.ItemCollectionBinding

class SearchAdapter(
    private val onItemClick: (BanknoteItem) -> Unit
): ListAdapter<BanknoteItem, SearchAdapter.ViewHolder>(DiffCallback()){
    inner class ViewHolder(val binding: ItemCollectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BanknoteItem) {
            /* title */
            binding.tvName.text = item.title
            /* year */
            val year = item.features?.find { it.title.contains("Year", ignoreCase = true) }?.value
            binding.tvYear.text = year ?: "Unknown year"
            /* thumb */
            val backupImage = R.drawable.image
            val image = item.images ?: emptyList()
            if (image.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(image.getOrNull(0)?: backupImage)
                    .error(backupImage)
                    .into(binding.ivFront)
            } else {
                binding.ivFront.setImageResource(backupImage)
            }
            if (image.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(image.getOrNull(1)?: backupImage)
                    .error(backupImage)
                    .into(binding.ivBack)
            } else {
                binding.ivBack.setImageResource(backupImage)
            }
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val binding = ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<BanknoteItem>() {
        override fun areItemsTheSame(oldItem: BanknoteItem, newItem: BanknoteItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: BanknoteItem, newItem: BanknoteItem): Boolean {
            return oldItem == newItem
        }
    }
}
