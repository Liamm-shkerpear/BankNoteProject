package com.example.banknoteproject.ui.onboarding.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.banknoteproject.databinding.FragmentStepThreeBinding
import com.example.banknoteproject.ui.onboarding.OnboardingViewModel
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
        binding.clSimple.setOnClickListener {
            updateUI(binding.clSimple)
            viewModel.markStepAnswered()
        }
        binding.clDetail.setOnClickListener {
            updateUI(binding.clDetail)
            viewModel.markStepAnswered()
        }
        binding.clValue.setOnClickListener {
            updateUI(binding.clValue)
            viewModel.markStepAnswered()
        }
        binding.clFocus.setOnClickListener {
            updateUI(binding.clFocus)
            viewModel.markStepAnswered()
        }

    }

    private fun updateUI(selectedOption: View) {
        binding.clSimple.isSelected = false
        binding.clDetail.isSelected = false
        binding.clValue.isSelected = false
        binding.clFocus.isSelected = false

        selectedOption.isSelected = true
    }
}
