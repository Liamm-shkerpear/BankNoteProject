package com.example.banknoteproject.ui.onboarding.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.banknoteproject.databinding.FragmentStepTwoBinding
import com.example.banknoteproject.ui.onboarding.OnboardingViewModel
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
        binding.clBanknote.setOnClickListener {
            updateUI(binding.clBanknote)
            viewModel.markStepAnswered()
        }
        binding.clCoin.setOnClickListener {
            updateUI(binding.clCoin)
            viewModel.markStepAnswered()
        }
        binding.clCheck.setOnClickListener {
            updateUI(binding.clCheck)
            viewModel.markStepAnswered()
        }
        binding.clLearn.setOnClickListener {
            updateUI(binding.clLearn)
            viewModel.markStepAnswered()
        }
    }

    private fun updateUI(selectedOption: View) {
        binding.clBanknote.isSelected = false
        binding.clCoin.isSelected = false
        binding.clCheck.isSelected = false
        binding.clLearn.isSelected = false

        selectedOption.isSelected = true
    }
}
