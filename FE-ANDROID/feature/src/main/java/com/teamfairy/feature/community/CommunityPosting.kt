package com.teamfairy.feature.community

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentCommunityPostingBinding

class CommunityPosting : BindingFragment<FragmentCommunityPostingBinding>(R.layout.fragment_community_posting) {
    override fun initView() {
        initCancelBtnClickListener()
        showKeyboard()
    }

    private fun initCancelBtnClickListener(){
        binding.ivCommunityPostingCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(
            binding.etCommunityPostingTitle,
            InputMethodManager.SHOW_IMPLICIT,
        )
    }
}