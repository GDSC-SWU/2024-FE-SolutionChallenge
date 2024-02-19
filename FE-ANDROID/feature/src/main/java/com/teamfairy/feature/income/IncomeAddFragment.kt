package com.teamfairy.feature.income

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.bottomsheet.IncomeAddPaydayBottomSheet
import com.teamfairy.feature.bottomsheet.IncomeSelectSalaryTypeBottomSheet
import com.teamfairy.feature.databinding.FragmentIncomeAddBinding
import com.teamfairy.feature.dialog.DeleteDialog
import com.teamfairy.feature.dialog.IncomeTaxDialog
import com.teamfairy.feature.dialog.IncomeWorkingDateDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class IncomeAddFragment : BindingFragment<FragmentIncomeAddBinding>(R.layout.fragment_income_add) {

    private val viewModel by activityViewModels<IncomeViewModel>()

    override fun initView() {
        initAddPaydayPickBottomSheet()
        initSelectSalaryTypeBottomSheet()
        setWorkingDateClickListener()
        observePayday()
        setTaxClickListener()
        observeSalaryType()
        setBackBtnClickLListener()
    }

    private fun initAddPaydayPickBottomSheet() {
        binding.tvIncomeAddPaydayPick.setOnClickListener {
            IncomeAddPaydayBottomSheet().show(
                parentFragmentManager,
                "payday",
            )
        }
    }

    private fun initSelectSalaryTypeBottomSheet() {
        binding.tvIncomeAddSalaryPick.setOnClickListener {
            IncomeSelectSalaryTypeBottomSheet().show(
                parentFragmentManager,
                "salarytype",
            )
        }
    }

    private fun setWorkingDateClickListener() {
        binding.ivIncomeAddMonthDateCalender.setOnClickListener {
            val dialog = IncomeWorkingDateDialog()
            dialog.show(childFragmentManager, "work")
        }
    }

    private fun observePayday() {
        viewModel.selectPayday.flowWithLifecycle(lifecycle).onEach {
            binding.tvIncomeAddPaydayPick.text = it
        }.launchIn(lifecycleScope)
    }

    private fun observeSalaryType() {
        viewModel.selectSalaryType.flowWithLifecycle(lifecycle).onEach {
            binding.tvIncomeAddSalaryPick.text = it
            if (it == "Hourly wage") {
                binding.tvIncomeAddLabelMonthDate.isVisible = true
                binding.ivIncomeAddMonthDateCalender.isVisible = true
            } else {
                binding.tvIncomeAddLabelMonthDate.isVisible = false
                binding.ivIncomeAddMonthDateCalender.isVisible = false
            }
        }.launchIn(lifecycleScope)
    }

    private fun setTaxClickListener() {
        binding.tvIncomeAddLabelTax.setOnClickListener {
            val dialog = IncomeTaxDialog()
            dialog.show(childFragmentManager, "tax")
        }
    }

    private fun setBackBtnClickLListener() {
        binding.appbarIncomeAdd.ivAppbarBack.setOnClickListener {
            val dialog = DeleteDialog("Do you want to cancel it", 1)
            dialog.show(childFragmentManager, "delete")
        }
    }
}