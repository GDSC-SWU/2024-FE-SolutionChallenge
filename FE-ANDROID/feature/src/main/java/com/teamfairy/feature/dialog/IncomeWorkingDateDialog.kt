package com.teamfairy.feature.dialog

import com.teamfairy.core_ui.base.BindingDialogFragment
import com.teamfairy.core_ui.util.context.dialogFragmentResize
import com.teamfairy.feature.R
import com.teamfairy.feature.bottomsheet.IncomeAddWorkingHourBottomSheet
import com.teamfairy.feature.bottomsheet.IncomeSelectSalaryTypeBottomSheet
import com.teamfairy.feature.databinding.DialogIncomeAddCalenderBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class IncomeWorkingDateDialog() :
    BindingDialogFragment<DialogIncomeAddCalenderBinding>(R.layout.dialog_income_add_calender) {
    override fun initView() {
        selectDate()
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this, 47.0f)
    }

    private fun selectDate() {
        binding.datepickerIncomeAddWork.setOnDateChangedListener { datePicker, year, monthOfYear, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, monthOfYear, dayOfMonth)
            val dateFormat = SimpleDateFormat("EEE, MMM d", Locale.ENGLISH)

            IncomeAddWorkingHourBottomSheet().show(
                childFragmentManager,
                dateFormat.format(calendar.time).toString(),
            )
        }
    }
}
