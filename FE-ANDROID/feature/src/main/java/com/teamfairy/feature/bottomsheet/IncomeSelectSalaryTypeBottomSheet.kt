package com.teamfairy.feature.bottomsheet

import androidx.fragment.app.activityViewModels
import com.teamfairy.core_ui.base.BindingBottomSheetFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.BottomsheetIncomeAddSalaryBinding
import com.teamfairy.feature.income.IncomeViewModel

class IncomeSelectSalaryTypeBottomSheet :
    BindingBottomSheetFragment<BottomsheetIncomeAddSalaryBinding>(R.layout.bottomsheet_income_add_salary) {

    private val viewModel by activityViewModels<IncomeViewModel>()

    override fun initView() {
        binding.tvSelectTypeSalaryHourly.isSelected = true
        selectType()
    }

    private fun selectType() {
        binding.rgSelectType.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.tv_select_type_salary_hourly -> viewModel.selectSalaryType("Hourly wage")
                R.id.tv_select_type_salary_weekly -> viewModel.selectSalaryType("Weekly wage")
                R.id.tv_select_type_salary_monthly -> viewModel.selectSalaryType("Monthly wage")
            }
        }
    }
}