package com.teamfairy.feature.dialog

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingDialogFragment
import com.teamfairy.core_ui.util.context.dialogFragmentResize
import com.teamfairy.feature.R
import com.teamfairy.feature.auth.SignInViewModel
import com.teamfairy.feature.databinding.DialogDeleteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteDialog(val title: String, val type: Int) :
    BindingDialogFragment<DialogDeleteBinding>(R.layout.dialog_delete) {
    private val viewModel by viewModels<SignInViewModel>()

    override fun initView() {
        binding.tvDialogDeleteTitle.text = title
        setYesBtnClickListener()
        setNoBtnClickListener()
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this, 48.0f)
    }

    private fun setYesBtnClickListener() {
        binding.tvDeleteDialogYes.setOnClickListener {
            when (type) {
                0 -> Unit //날짜 취소
                1 -> {
                    //수입 취소
                    findNavController().popBackStack()
                }

                2 -> {
                    //게시물 삭제
                }

                3 -> {
                    //게시물 신고
                }

                4 -> {
                    viewModel.saveCheckLogin(false)
                    findNavController().navigate(R.id.action_my_page_to_signIn)
                }
                6 -> {
                    //게시글 취소
                    findNavController().popBackStack()
                }
            }
            dismiss()
        }
    }

    private fun setNoBtnClickListener() {
        binding.tvDeleteDialogNo.setOnClickListener {
            dismiss()
        }
    }
}
