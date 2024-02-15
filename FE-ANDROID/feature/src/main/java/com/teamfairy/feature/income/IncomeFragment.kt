package com.teamfairy.feature.income

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentIncomeBinding
import com.teamfairy.feature.util.Key.KEY_INCOME_CARD
import java.time.LocalDate

class IncomeFragment : BindingFragment<FragmentIncomeBinding>(R.layout.fragment_income) {
    override fun initView() {
        initIncomeCardAdapter()
    }

    private fun initIncomeCardAdapter() {
        val list = listOf(
            IncomeCardEntity(1, "TEST", "15", "1", "5,000"),
            IncomeCardEntity(2, "TEST", "16", "1", "5,000"),
            IncomeCardEntity(3, "TEST", "17", "1", "5,000"),
            IncomeCardEntity(4, "TEST", "18", "1", "5,000")
        )

        binding.rvIncome.adapter = IncomeCardAdapter(onClickReceiveSalary = {},
            onMoveToIncomeDetailClick = { incomeCardData ->
                navigateToIncomeDetailFragment(
                    incomeCardData
                )
            },
            today = dayOfMonth
        ).apply {
            submitList(list)
        }
    }

    private fun navigateToIncomeDetailFragment(incomeCardEntity: IncomeCardEntity) {
        findNavController().navigate(
            R.id.action_income_to_incomeDetail,
            bundleOf(KEY_INCOME_CARD to IncomeCard(incomeCardEntity))
        )
    }

    companion object {
        val dayOfMonth = LocalDate.now().dayOfMonth
    }
}