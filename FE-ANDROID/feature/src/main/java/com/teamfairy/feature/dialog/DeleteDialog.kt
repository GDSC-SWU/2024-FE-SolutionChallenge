package com.teamfairy.feature.dialog

import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingDialogFragment
import com.teamfairy.core_ui.util.context.dialogFragmentResize
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.DialogDeleteBinding

class DeleteDialog(val title: String,val type:Int) :
    BindingDialogFragment<DialogDeleteBinding>(R.layout.dialog_delete) {
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
        when(type){
            0 -> dismiss()
            1 -> {
                findNavController().popBackStack()
                dismiss()
            }
        }
    }

    private fun setNoBtnClickListener() {
        binding.tvDeleteDialogNo.setOnClickListener {
            dismiss()
        }
    }
}
