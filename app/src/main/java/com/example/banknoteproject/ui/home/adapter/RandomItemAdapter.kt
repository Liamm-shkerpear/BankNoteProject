package com.example.banknoteproject.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banknoteproject.R
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.databinding.ItemRandomBinding

class RandomItemAdapter(
    private val onItemClick: (BanknoteItem) -> Unit
) : ListAdapter<BanknoteItem, RandomItemAdapter.ViewHolder>(DiffCallBack()) {
    inner class ViewHolder(val binding: ItemRandomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BanknoteItem) {
            /* title */
            binding.tvTitle.text = item.title
            /* year */
            val year = item.features?.find {
                it.title.contains("Year", ignoreCase = true)
            }
            binding.tvYear.text = year?.value ?: "Unknown year"
            /* price */
            val price = item.pricing?.filter { it.price.isNotBlank() }
            val displayPrice = when {
                price.isNullOrEmpty() -> "$ 0"
                price.size == 1 -> price[0].price
                else -> "${price[0].price} - ${price[price.lastIndex].price}"
            }
            binding.tvPrice.text = displayPrice
            /* thumb */
            val backupImage = R.drawable.image
            val image = item.images ?: emptyList()

            if (image.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(image[0])
                    .error(backupImage)
                    .into(binding.ivFront)
            } else {
                binding.ivFront.setImageResource(backupImage)
            }
            if (image.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(image[1])
                    .error(backupImage)
                    .into(binding.ivBack)
            } else {
                binding.ivBack.setImageResource(backupImage)
            }

            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val binding = ItemRandomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
