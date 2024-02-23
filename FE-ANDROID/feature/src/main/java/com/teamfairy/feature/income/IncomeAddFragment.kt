package com.teamfairy.feature.income

import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.core_ui.view.UiState
import com.teamfairy.domain.entity.AddIncomeEntity
import com.teamfairy.domain.entity.WorkEntity
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@AndroidEntryPoint
class IncomeAddFragment : BindingFragment<FragmentIncomeAddBinding>(R.layout.fragment_income_add) {

    private val viewModel by activityViewModels<IncomeViewModel>()

    override fun initView() {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        initAddPaydayPickBottomSheet()
        initSelectSalaryTypeBottomSheet()
        setWorkingDateClickListener()
        observePayday()
        setTaxClickListener()
        observeSalaryType()
        setBackBtnClickLListener()
        observe()
        setCompletionClickListener()
    }

    private fun observe() {
        viewModel.postAddIncome.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> findNavController().navigateUp()
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun completionAddIncome() {
        binding.btnIncomeAdd.setOnClickListener {
            val company = binding.etIncomeAddCompanyName.text.toString()
            val payday = extractFirstNumberFromString(binding.tvIncomeAddPaydayPick.text.toString())

            with(LocalDate.now()) {
                val fromWorkDay = formatDate(year, monthValue, dayOfMonth)
                val toWorkDay = payday?.toInt()?.let { formatDate(year, monthValue, it) }
                val taxYn = binding.switchItemIncomeCard.isChecked
                if (binding.tvIncomeAddSalaryPick.text.toString() == "Hourly wage") {
                    val salaryType = binding.tvIncomeAddSalaryPick.text.toString().replace(" ", "_")
                        .uppercase(Locale.getDefault())
                } else {
                    val salaryType =
                        binding.tvIncomeAddSalaryPick.text.toString().replace("wage", "pay")
                            .replace(" ", "_")
                            .uppercase(Locale.getDefault())

                    val tblWorks = listOf(
                        WorkEntity(
                            binding.etIncomeAddSalary.text.toString().toDouble(), null, null
                        )
                    )

                    viewModel.postAddIncome(
                        AddIncomeEntity(
                            company,
                            payday ?: "0",
                            salaryType,
                            tblWorks,
                            fromWorkDay,
                            toWorkDay ?: "2022-01-01",
                            taxYn
                        )
                    )
                }
            }
        }
    }

    private fun extractFirstNumberFromString(input: String): String? {
        val regex = Regex("\\d+")
        val matchResult = regex.find(input)
        return matchResult?.value
    }

    fun formatDate(year: Int, month: Int, day: Int): String {
        val date = LocalDate.of(year, month, day)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
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
            binding.tvIncomeAddPaydayPick.isSelected = true
        }.launchIn(lifecycleScope)
    }

    private fun observeSalaryType() {
        viewModel.selectSalaryType.flowWithLifecycle(lifecycle).onEach {
            binding.tvIncomeAddSalaryPick.text = it
            binding.tvIncomeAddSalaryPick.isSelected = true
            if (it == "Hourly wage") {
                binding.tvIncomeAddLabelMonthDate.isVisible = true
                binding.ivIncomeAddMonthDateCalender.isVisible = true
            } else {
                binding.tvIncomeAddLabelMonthDate.isVisible = false
                binding.ivIncomeAddMonthDateCalender.isVisible = false
            }

            binding.etIncomeAddSalary.doAfterTextChanged { text ->
                if (binding.etIncomeAddSalary.length() > 0) {
                    binding.btnIncomeAdd.isSelected = true
                    //setCompletionClickListener()
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setCompletionClickListener() {
        binding.btnIncomeAdd.setOnClickListener {
            completionAddIncome()
        }
    }

    private fun setTaxClickListener() {
        binding.tvIncomeAddLabelTax.setOnClickListener {
            val dialog = IncomeTaxDialog()
            dialog.show(childFragmentManager, "tax")
        }
    }

    private fun setBackBtnClickLListener() {
        binding.appbarIncomeAdd.ivAppbarBack.setOnClickListener {
            val dialog = DeleteDialog("Do you want to cancel it?", 1, -1)
            dialog.show(childFragmentManager, "delete")
        }
    }
}