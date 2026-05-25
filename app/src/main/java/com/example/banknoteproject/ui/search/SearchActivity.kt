package com.example.banknoteproject.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.databinding.ActivitySearchBinding
import com.example.banknoteproject.ui.detail.DetailActivity
import com.example.banknoteproject.ui.search.adapter.SearchAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private val searchAdapter by lazy {
        SearchAdapter(
            onItemClick = ::itemClickHandle
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()
        bindViewModel()
    }

    private fun initView() {
        binding.rvSearch.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchAdapter
        }
    }

    private fun initListener() {
        binding.btnBack.setOnClickListener { finish() }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val keyword = query ?: ""
                viewModel.searchItems(keyword)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    viewModel.searchItems("")
                }
                return false
            }
        })
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.searchResults.collect { list ->
                searchAdapter.submitList(list)
            }
        }
        lifecycleScope.launch {
            viewModel.isEmpty.collect { empty ->
                if (empty) {
                    binding.clNoData.visibility = View.VISIBLE
                    binding.rvSearch.visibility = View.GONE
                } else {
                    binding.clNoData.visibility = View.GONE
                    binding.rvSearch.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun itemClickHandle(item: BanknoteItem) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("EXTRA_DATA", item)
        }
        startActivity(intent)
    }
}

