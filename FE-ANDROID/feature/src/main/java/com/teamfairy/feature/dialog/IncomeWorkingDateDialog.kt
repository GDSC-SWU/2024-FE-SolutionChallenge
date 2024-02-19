package com.teamfairy.feature.dialog

import com.teamfairy.core_ui.base.BindingDialogFragment
import com.teamfairy.core_ui.util.context.dialogFragmentResize
import com.teamfairy.feature.R
import com.teamfairy.feature.bottomsheet.IncomeAddWorkingHourBottomSheet
import com.teamfairy.feature.databinding.DialogIncomeAddCalenderBinding
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class IncomeWorkingDateDialog() :
    BindingDialogFragment<DialogIncomeAddCalenderBinding>(R.layout.dialog_income_add_calender) {
    override fun initView() {
        initCalenderAdapter()
        setTodayYearMonthText()
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this, 20.0f)
    }

    private fun initCalenderAdapter() {
        binding.rvWhenCalenderDate.adapter =
            CalenderAdapter(onDateClick = {
                IncomeAddWorkingHourBottomSheet().show(
                    childFragmentManager,
                    setDate(it),
                )
            }).apply {
                submitList(getCalendarDateList())
            }
    }

    private fun setDate(day: String): String {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val thisMonth = SimpleDateFormat("MMM", Locale.ENGLISH).format(calendar.time)

        return "$currentYear, $thisMonth $day"
    }


    private fun setTodayYearMonthText() {
        binding.tvWhenCalenderYearMonth.text = setDate("")
    }

    private val currentDate = LocalDate.now()

    private fun getCalendarDateList(): List<String> {
        val firstDayOfMonth = currentDate.withDayOfMonth(1)
        val dayOfWeekValue = firstDayOfMonth.dayOfWeek.value

        val calendarDateList = mutableListOf<String>()

        addEmptyDaysToList(calendarDateList, dayOfWeekValue)
        addDaysOfMonthToList(calendarDateList, firstDayOfMonth)

        return calendarDateList
    }

    private fun addEmptyDaysToList(list: MutableList<String>, dayOfWeekValue: Int) {
        //현재 달의 1일이 요일에 따라 빈 값을 추가한 후 숫자를 리스트에 넣기, 단 1일이 일요일이면 넣지 않는다.
        if (dayOfWeekValue != DayOfWeek.SUNDAY.value) {
            for (i in 1 until dayOfWeekValue + 1) {
                list.add("")
            }
        }
    }

    private fun addDaysOfMonthToList(list: MutableList<String>, firstDayOfMonth: LocalDate) {
        //나머지 일 채우기
        val daysInMonth = firstDayOfMonth.lengthOfMonth()
        for (i in 1..daysInMonth) {
            list.add(i.toString())
        }
    }

    companion object {
        const val DATE_TIME_FORMAT = "yyyy MM월"
    }
}
