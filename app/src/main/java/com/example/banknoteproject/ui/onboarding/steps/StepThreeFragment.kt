package com.example.banknoteproject.ui.onboarding.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.banknoteproject.databinding.FragmentStepThreeBinding
import com.example.banknoteproject.ui.onboarding.OnboardingViewModel
import com.example.banknoteproject.utils.AppConstants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class StepThreeFragment : Fragment() {
    private val viewModel: OnboardingViewModel by activityViewModel()
    private var _binding: FragmentStepThreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepThreeBinding.inflate(inflater, container, false)
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
        binding.clSimple.setOnClickListener { viewModel.updateStepThree(AppConstants.SIMPLE) }
        binding.clDetail.setOnClickListener { viewModel.updateStepThree(AppConstants.DETAIL) }
        binding.clValue.setOnClickListener { viewModel.updateStepThree(AppConstants.VALUE) }
        binding.clFocus.setOnClickListener { viewModel.updateStepThree(AppConstants.FOCUS) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onboardingData.collect { state ->
                    updateUI(state.stepThree)
                }
            }
        }
    }

    private fun updateUI(selectedOption: String) {
        binding.clSimple.isSelected = false
        binding.clDetail.isSelected = false
        binding.clValue.isSelected = false
        binding.clFocus.isSelected = false

        when (selectedOption) {
            AppConstants.SIMPLE -> binding.clSimple.isSelected = true
            AppConstants.DETAIL -> binding.clDetail.isSelected = true
            AppConstants.VALUE -> binding.clValue.isSelected = true
            AppConstants.FOCUS -> binding.clFocus.isSelected = true
        }
    }
}
