package com.teamfairy.feature.income

import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.bottomsheet.IncomeAddPaydayBottomSheet
import com.teamfairy.feature.databinding.FragmentIncomeAddBinding

class IncomeAddFragment : BindingFragment<FragmentIncomeAddBinding>(R.layout.fragment_income_add) {
    override fun initView() {
        binding.tvIncomeAddPaydayPick.setOnClickListener {
            IncomeAddPaydayBottomSheet().show(
                parentFragmentManager,
                "bottomsheet",
            )
        }
    }
}