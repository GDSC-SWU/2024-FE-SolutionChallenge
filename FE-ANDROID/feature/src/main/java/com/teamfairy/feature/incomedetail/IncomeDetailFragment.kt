package com.teamfairy.feature.incomedetail

import androidx.recyclerview.widget.ConcatAdapter
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentIncomeDetailBinding
import com.teamfairy.feature.income.IncomeCardAdapter

class IncomeDetailFragment :
    BindingFragment<FragmentIncomeDetailBinding>(R.layout.fragment_income_detail) {
    override fun initView() {
        concatIncomeDetailAdapter()
    }

    private fun concatIncomeDetailAdapter(){
        val incomeCardAdapter = IncomeCardAdapter(
            onMoveToIncomeDetailClick = {},
            onClickReceiveSalary = {} ,
            today = 1
        ).apply {

        }

        val incomeWorkCheckAdapter = IncomeWorkCheckAdapter().apply {  }

        binding.rvIncomeDetail.adapter = ConcatAdapter(incomeCardAdapter,incomeWorkCheckAdapter)
    }
}