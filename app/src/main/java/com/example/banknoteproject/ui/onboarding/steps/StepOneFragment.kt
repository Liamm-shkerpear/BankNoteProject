package com.example.banknoteproject.ui.onboarding.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.banknoteproject.databinding.FragmentStepOneBinding
import com.example.banknoteproject.ui.onboarding.OnboardingViewModel
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
        binding.clBanknote.setOnClickListener {
            updateUI(binding.clBanknote)
            viewModel.markStepOneAnswered()
        }
        binding.clCoin.setOnClickListener {
            updateUI(binding.clCoin)
            viewModel.markStepOneAnswered()
        }
        binding.clBoth.setOnClickListener {
            updateUI(binding.clBoth)
            viewModel.markStepOneAnswered()
        }
    }


    private fun updateUI(selectedOption: View) {
        binding.clBanknote.isSelected = false
        binding.clCoin.isSelected = false
        binding.clBoth.isSelected = false

        selectedOption.isSelected = true
    }
}
