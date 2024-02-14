package com.teamfairy.feature.income

import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentIncomeBinding
import java.time.LocalDate

class IncomeFragment : BindingFragment<FragmentIncomeBinding>(R.layout.fragment_income) {
    override fun initView() {
        initIncomeCardAdapter()
    }

    private fun initIncomeCardAdapter() {
        val list = listOf(
            IncomeCardEntity(1, "TEST", "15", "1", "5,000"),
            IncomeCardEntity(1, "TEST", "16", "1", "5,000"),
            IncomeCardEntity(1, "TEST", "17", "1", "5,000"),
            IncomeCardEntity(1, "TEST", "18", "1", "5,000")
        )

        binding.rvIncome.adapter = IncomeCardAdapter(
            onClickReceiveSalary = {},
            onMoveToIncomeDetailClick = {},
            today = dayOfMonth
        ).apply {
            submitList(list)
        }
    }

    companion object {
        val dayOfMonth = LocalDate.now().dayOfMonth
    }
}