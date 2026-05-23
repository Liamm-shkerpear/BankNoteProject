package com.example.banknoteproject.ui.collection

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
import com.example.banknoteproject.databinding.FragmentCollectionBinding
import com.example.banknoteproject.ui.collection.adapter.CollectionAdapter
import com.example.banknoteproject.ui.detail.DetailActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionFragment: Fragment() {
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CollectionViewModel by viewModel()
    private val collectionAdapter by lazy {
        CollectionAdapter(
            onItemClick = ::itemClickHandle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        bindViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        binding.rvCollection.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = collectionAdapter
        }
    }

    private fun bindViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.collectionItems.collect { items ->
                    collectionAdapter.submitList(items)
                }
            }
        }
    }

    private fun itemClickHandle(item: BanknoteItem) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("EXTRA_DATA", item)
        }
        startActivity(intent)
    }
}
