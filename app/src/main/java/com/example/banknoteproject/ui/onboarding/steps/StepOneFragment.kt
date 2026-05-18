package com.example.banknoteproject.ui.onboarding.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.banknoteproject.databinding.FragmentStepOneBinding
import com.example.banknoteproject.ui.onboarding.OnboardingViewModel
import com.example.banknoteproject.utils.AppConstants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class StepOneFragment : Fragment() {
    private val viewModel: OnboardingViewModel by activityViewModel()
    private var _binding: FragmentStepOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initClickListener() {
        binding.clBanknote.setOnClickListener { viewModel.updateStepOne(AppConstants.BANKNOTE) }
        binding.clCoin.setOnClickListener { viewModel.updateStepOne(AppConstants.COIN) }
        binding.clBoth.setOnClickListener { viewModel.updateStepOne(AppConstants.BOTH) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onboardingData.collect { state ->
                    updateUI(state.stepOne)
                }
            }
        }
    }


    private fun updateUI(selectedOption: String) {
        binding.clBanknote.isSelected = false
        binding.clCoin.isSelected = false
        binding.clBoth.isSelected = false

        when (selectedOption) {
            AppConstants.BANKNOTE  -> binding.clBanknote.isSelected = true
            AppConstants.COIN -> binding.clCoin.isSelected = true
            AppConstants.BOTH -> binding.clBoth.isSelected = true
        }
    }
}
