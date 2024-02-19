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
    }

    private fun generateHourLabels(): Array<String> {
        val hours = 24
        val timeArray = Array(hours) { "" }

        var startHour = 0
        var startMinute = 0

        for (i in 0 until hours) {
            val timeString = "%02d:%02d".format(startHour, startMinute)
            timeArray[i] = timeString

            // 30분 단위로 시간 증가
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
            val dialog = DeleteDialog("Delete this schedule?", 0)
            dialog.show(childFragmentManager, "delete")
        }
    }
}