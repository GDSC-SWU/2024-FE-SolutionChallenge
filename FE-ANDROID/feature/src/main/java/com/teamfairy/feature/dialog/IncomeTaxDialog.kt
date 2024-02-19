package com.teamfairy.feature.dialog

import com.teamfairy.core_ui.base.BindingDialogFragment
import com.teamfairy.core_ui.util.context.dialogFragmentResize
import com.teamfairy.feature.R
import com.teamfairy.feature.bottomsheet.IncomeSelectSalaryTypeBottomSheet
import com.teamfairy.feature.databinding.DialogIncomeAddCalenderBinding
import com.teamfairy.feature.databinding.DialogIncomeTaxBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class IncomeTaxDialog() :
    BindingDialogFragment<DialogIncomeTaxBinding>(R.layout.dialog_income_tax) {
    override fun initView() {
       setCancelBtnClickListener()
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this, 20.0f)
    }

    private fun setCancelBtnClickListener(){
        binding.ivIncomeTaxCancel.setOnClickListener {
            dismiss()
        }
    }
}
