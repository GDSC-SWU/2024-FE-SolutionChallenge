package com.teamfairy.feature.community

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.domain.entity.PostingFeedEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentCommunityPostingBinding
import com.teamfairy.feature.dialog.DeleteDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityPosting :
    BindingFragment<FragmentCommunityPostingBinding>(R.layout.fragment_community_posting) {

    private val viewModel by viewModels<CommunityViewModel>()

    override fun initView() {
        initCancelBtnClickListener()
        showKeyboard()
        binding.chipCommunityDaily.isSelected = true
        setShareDeliveryCheckBoxClickListener()
        setDailyCheckBoxClickListener()
        setPostBtnClickListener()
        setTitleMax()
    }

    private fun setTitleMax() {
        binding.etCommunityPostingTitle.doAfterTextChanged { text ->
            binding.tvCommunityPostingTitleLength.text =
                binding.etCommunityPostingTitle.text.length.toString() + "/20"
        }

        binding.etCommunityPostingContent.doAfterTextChanged { text ->
            binding.tvCommunityPostingContentLength.text =
                binding.etCommunityPostingContent.text.length.toString() + "/500"
        }
    }

    private fun setPostBtnClickListener() {
        binding.ivCommunityPostingComplete.setOnClickListener {
            viewModel.postCommunityPosting(
                PostingFeedEntity(
                    getFeedType(),
                    binding.etCommunityPostingTitle.text.toString(),
                    binding.etCommunityPostingContent.text.toString(),
                    "THUMB_NAIL"
                )
            )
            findNavController().popBackStack()
        }
    }

    private fun getFeedType(): String {
        return if (binding.chipCommunityDaily.isSelected) {
            "DAILY"
        } else {
            "NOTICE"
        }
    }

    private fun initCancelBtnClickListener() {
        binding.ivCommunityPostingCancel.setOnClickListener {
            val dialog = DeleteDialog("Do you want to cancel it?", 6,-1)
            dialog.show(childFragmentManager, "delete")
        }
    }

    private fun setDailyCheckBoxClickListener() =
        binding.chipCommunityDaily.setOnClickListener {
            if (binding.chipCommunityShareDelivery.isSelected) binding.chipCommunityShareDelivery.isSelected =
                false
            binding.chipCommunityDaily.isSelected = true
        }

    private fun setShareDeliveryCheckBoxClickListener() =
        binding.chipCommunityShareDelivery.setOnClickListener {
            if (binding.chipCommunityDaily.isSelected) binding.chipCommunityDaily.isSelected =
                false
            binding.chipCommunityShareDelivery.isSelected = true
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