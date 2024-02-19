package com.teamfairy.feature.bottomsheet

import com.teamfairy.core_ui.base.BindingBottomSheetFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.BottomsheetIncomeAddPaydayBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class IncomeAddPaydayBottomSheet :
    BindingBottomSheetFragment<BottomsheetIncomeAddPaydayBinding>(R.layout.bottomsheet_income_add_payday) {
    override fun initView() {
        val list = generateDayLabels()
        binding.numberpickerBottomsheetIncomeAddPayday.maxValue = list.size
        binding.numberpickerBottomsheetIncomeAddPayday.minValue = 1
        binding.numberpickerBottomsheetIncomeAddPayday.displayedValues = list
    }

    private fun generateDayLabels(): Array<String> {
        val currentDate = LocalDate.now()
        val year = currentDate.year
        val month = currentDate.monthValue
        val formatter = DateTimeFormatter.ofPattern("d")
        val lastDay = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1).dayOfMonth
        val dayLabels = Array(lastDay) { "" }
        for (day in 1..lastDay) {
            val dayStr = day.toString()
            val suffix = when (day % 10) {
                1 -> "st"
                2 -> "nd"
                3 -> "rd"
                else -> "th"
            }
            val label = "$dayStr$suffix"
            dayLabels[day - 1] = label
        }
        return dayLabels
    }
}
