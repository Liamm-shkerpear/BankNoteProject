package com.example.banknoteproject.ui.detail

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.banknoteproject.R
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.domain.entities.Feature
import com.example.banknoteproject.databinding.ActivityDetailBinding
import com.example.banknoteproject.ui.detail.adapter.FeatureAdapter

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val featureAdapter by lazy {
        FeatureAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()
    }

    private fun initView() {
        val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_DATA", BanknoteItem::class.java)
        } else {
            intent.getParcelableExtra("EXTRA_DATA")
        }
        item?.run {
            /* thumb */
            val backupImage = R.drawable.image
            val image = item.images ?: emptyList()
            if (image.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(image[0])
                    .error(backupImage)
                    .into(binding.ivDetailFront)
            } else {
                binding.ivDetailFront.setImageResource(backupImage)
            }
            if (image.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(image[1])
                    .error(backupImage)
                    .into(binding.ivDetailBack)
            } else {
                binding.ivDetailBack.setImageResource(backupImage)
            }
            /* title */
            binding.tvDetailTitle.text = item.title
            /* price */
            val price = item.pricing?.filter { it.price.isNotBlank() }
            val displayPrice = when {
                price.isNullOrEmpty() -> "$ 0"
                price.size == 1 -> price[0].price
                else -> "${price[0].price} - ${price[price.lastIndex].price}"
            }
            binding.tvDetailPrice.text = displayPrice
            /* year */
            val year = item.features?.find {
                it.title.contains("Year", ignoreCase = true)
            }
            binding.tvYearValue.text = year?.value ?: "Unknown year"
            /* value */
            val value = item.features?.find {
                it.title.contains("Value", ignoreCase = true)
            }
            binding.tvValueValue.text = value?.value ?: "Unknown value"
            /* description */
            val obvDes = item.obverse?.description?.takeIf { it.isNotBlank() }?.trim()
            val revDes = item.reverse?.description?.takeIf { it.isNotBlank() }?.trim()
            val checkObv = !obvDes.isNullOrBlank()
            val checkRev = !revDes.isNullOrBlank()
            val displayDes = when {
                checkObv && checkRev -> "$obvDes\n$revDes"
                checkObv -> obvDes
                checkRev -> revDes
                else -> "No description available"
            }
            binding.tvDes.text = displayDes
            /* feature */
            binding.rvFeatures.adapter = featureAdapter
            val featureList = item.features ?: emptyList()
            if (featureList.isNotEmpty()) {
                featureAdapter.submitList(featureList)
            } else {
                val emptyItem = Feature(title = "", value = "No data info")
                featureAdapter.submitList(listOf(emptyItem))
            }
            /* lettering */
            val obvLet = item.obverse?.lettering?.takeIf { it.isNotBlank() }?.trim()
            val revLet = item.reverse?.lettering?.takeIf { it.isNotBlank() }?.trim()
            val checkObvLet = !obvLet.isNullOrBlank()
            val checkRevLet = !revLet.isNullOrBlank()
            val displayLet = when {
                checkObvLet && checkRevLet -> "$obvLet\n$revLet"
                checkObvLet -> obvLet
                checkRevLet -> revLet
                else -> "No lettering available"
            }
            binding.tvLetteringContent.text = displayLet
        } ?:showError()
    }
    private fun showError() {
        Toast.makeText(this, "Không thể tải thông tin chi tiết!", Toast.LENGTH_SHORT).show()
        finish()
    }
    private fun initListener() {
        initBackButton()
    }

    private fun initBackButton() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
