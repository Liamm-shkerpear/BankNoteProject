package com.example.banknoteproject.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.databinding.ActivitySearchBinding
import com.example.banknoteproject.ui.detail.DetailActivity
import com.example.banknoteproject.ui.search.adapter.SearchAdapter
import com.example.banknoteproject.utils.AppConstants
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

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val visibleItemCount = layoutManager.childCount
                        val totalCount = layoutManager.itemCount
                        val firstItemPos = layoutManager.findFirstVisibleItemPosition()

                        if ((visibleItemCount + firstItemPos + 5) >= totalCount) {
                            val currentQuery = binding.searchView.query.toString()
                            if (currentQuery.isBlank()) {
                                viewModel.getAllData(isRefresh = false)
                            } else {
                                viewModel.searchItems(currentQuery, isLoadMore = true)
                            }
                        }
                    }
                }
            })
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchResults.collect { list ->
                    searchAdapter.submitList(list)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
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
    }

    private fun itemClickHandle(item: BanknoteItem) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(AppConstants.EXTRA_DATA, item)
        }
        startActivity(intent)
    }
}

