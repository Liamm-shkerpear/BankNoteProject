package com.example.banknoteproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.databinding.FragmentHomeBinding
import com.example.banknoteproject.ui.detail.DetailActivity
import com.example.banknoteproject.ui.home.adapter.RandomItemAdapter
import com.example.banknoteproject.ui.home.adapter.RecentItemAdapter
import com.example.banknoteproject.ui.search.SearchActivity
import com.example.banknoteproject.utils.AppConstants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.jvm.java

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()
    private val recentItemAdapter by lazy {
        RecentItemAdapter(
            onItemClick = ::itemClickHandle
        )
    }
    private val randomItemAdapter by lazy {
        RandomItemAdapter(
            onItemClick = ::itemClickHandle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        binding.rvRecent.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recentItemAdapter
        }
        binding.rvRandomBanknote.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = randomItemAdapter
        }
        observeData()
        viewModel.getHomeData()
    }

    private fun initListener() {
        binding.cvSearchBar.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recentData.collect { banknoteItems ->
                    if (banknoteItems.isNotEmpty()) {
                        recentItemAdapter.submitList(banknoteItems)
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.randomData.collect { banknoteItems ->
                    randomItemAdapter.submitList(banknoteItems)
                }
            }
        }
    }

    private fun itemClickHandle(item: BanknoteItem) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(AppConstants.EXTRA_DATA, item)
        }
        startActivity(intent)
    }
}
