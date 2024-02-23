package com.teamfairy.feature.bottomsheet

import com.teamfairy.core_ui.base.BindingBottomSheetFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.BottomsheetIncomeAddWorkingHourBinding
import com.teamfairy.feature.dialog.DeleteDialog

class IncomeAddWorkingHourBottomSheet :
    BindingBottomSheetFragment<BottomsheetIncomeAddWorkingHourBinding>(R.layout.bottomsheet_income_add_working_hour) {
    override fun initView() {
        val list = generateHourLabels()
        binding.numberpickerIncomeAddWorkingHour.maxValue = list.size
        binding.numberpickerIncomeAddWorkingHour.minValue = 1
        binding.numberpickerIncomeAddWorkingHour.displayedValues = list
        setCancelBtnClickListener()
        binding.tvIncomeAddWorkingHourDate.text = tag
        binding.done.setOnClickListener {
            dismiss()
        }
    }

    private fun generateHourLabels(): Array<String> {
        val hours = 24
        val timeArray = Array(hours) { "" }
        var startHour = 0
        var startMinute = 0

        for (i in 0 until hours) {
            val hour = startHour
            val timeString = when (val minute = startMinute) {
                0 -> "$hour hour"
                30 -> "$hour hour 30min"
                else -> "$minute min"
            }

            timeArray[i] = timeString

            startMinute += 30
            if (startMinute == 60) {
                startHour++
                startMinute = 0
            }
        }
        return timeArray
    }

    private fun setCancelBtnClickListener() {
        binding.ivAddWorkingHourCancel.setOnClickListener {
            val dialog = DeleteDialog("Delete this schedule?", 0,-1)
            dialog.show(childFragmentManager, "delete")
        }
    }
}