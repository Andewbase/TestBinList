package com.example.testcft.screen.dialog

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.testcft.databinding.FragmentDialogErrorBinding
import com.example.testcft.screen.base.BaseDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorFragmentDialog: BaseDialogFragment<FragmentDialogErrorBinding>(FragmentDialogErrorBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            val action = ErrorFragmentDialogDirections.actionErrorFragmentDialogToMainFragment()
            findNavController().navigate(action)
        }

    }

}