package com.example.banknoteproject.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banknoteproject.R
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.databinding.ItemRecentBinding

class RecentItemAdapter(
    private val onItemClick: (BanknoteItem) -> Unit
) : ListAdapter<BanknoteItem, RecentItemAdapter.ViewHolder>(DiffCallBack()) {
    inner class ViewHolder(val binding: ItemRecentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BanknoteItem) {
            /* price */
            val price = item.pricing?.firstOrNull()?.price
            val displayPrice = if (price.isNullOrBlank()) "$0" else price
            binding.tvPrice.text = displayPrice
            /* country */
            val country = item.countryRegion.split(" › ").firstOrNull()?.trim() ?: "Unknown"
            binding.tvCountry.text = country
            /* thumb */
            val backupImage = R.drawable.image
            val image = item.images ?: emptyList()
            if (image.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(image.getOrNull(0) ?: backupImage)
                    .error(backupImage)
                    .into(binding.ivFront)
            } else {
                binding.ivFront.setImageResource(backupImage)
            }
            if (image.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(image.getOrNull(1) ?: backupImage)
                    .error(backupImage)
                    .into(binding.ivBack)
            } else {
                binding.ivBack.setImageResource(backupImage)
            }
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallBack : DiffUtil.ItemCallback<BanknoteItem>() {
        override fun areItemsTheSame(oldItem: BanknoteItem, newItem: BanknoteItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BanknoteItem, newItem: BanknoteItem): Boolean {
            return oldItem == newItem
        }

    }
}
