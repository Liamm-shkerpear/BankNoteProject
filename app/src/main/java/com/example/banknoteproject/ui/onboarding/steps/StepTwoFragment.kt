package com.example.banknoteproject.ui.onboarding.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.banknoteproject.databinding.FragmentStepTwoBinding
import com.example.banknoteproject.ui.onboarding.OnboardingViewModel
import com.example.banknoteproject.utils.AppConstants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class StepTwoFragment : Fragment() {
    private val viewModel: OnboardingViewModel by activityViewModel()
    private var _binding: FragmentStepTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepTwoBinding.inflate(inflater, container, false)
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
        binding.clBanknote.setOnClickListener { viewModel.updateStepTwo(AppConstants.BANKNOTE_IDENTIFY) }
        binding.clCoin.setOnClickListener { viewModel.updateStepTwo(AppConstants.COIN_IDENTIFY) }
        binding.clCheck.setOnClickListener { viewModel.updateStepTwo(AppConstants.CHECK_VALUE) }
        binding.clLearn.setOnClickListener { viewModel.updateStepTwo(AppConstants.LEARN) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onboardingData.collect { state ->
                    updateUI(state.stepTwo)
                }
            }
        }
    }

    private fun updateUI(selectedOption: String) {
        binding.clBanknote.isSelected = false
        binding.clCoin.isSelected = false
        binding.clCheck.isSelected = false
        binding.clLearn.isSelected = false

        when (selectedOption) {
            AppConstants.BANKNOTE_IDENTIFY -> binding.clBanknote.isSelected = true
            AppConstants.COIN_IDENTIFY -> binding.clCoin.isSelected = true
            AppConstants.CHECK_VALUE -> binding.clCheck.isSelected = true
            AppConstants.LEARN -> binding.clLearn.isSelected = true
        }
    }
}
