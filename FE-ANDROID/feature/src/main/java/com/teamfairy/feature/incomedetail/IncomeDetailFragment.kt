package com.teamfairy.feature.incomedetail

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.core_ui.util.fragment.getParcelable
import com.teamfairy.core_ui.view.UiState
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.domain.entity.WorkCheckEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentIncomeDetailBinding
import com.teamfairy.feature.income.IncomeCard
import com.teamfairy.feature.income.IncomeCardAdapter
import com.teamfairy.feature.income.IncomeViewModel
import com.teamfairy.feature.util.Key.KEY_INCOME_CARD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class IncomeDetailFragment :
    BindingFragment<FragmentIncomeDetailBinding>(R.layout.fragment_income_detail) {
    private lateinit var incomeCardAdapter: IncomeCardAdapter
    private lateinit var incomeWorkCheckAdapter: IncomeWorkCheckAdapter
    private val viewModel by viewModels<IncomeViewModel>()

    override fun initView() {
        getParcelable(KEY_INCOME_CARD, IncomeCard::class.java)?.let { viewModel.postIncomeDetail(it.incomeId) }
        initIncomeCardAdapter()
        concatIncomeDetailAdapter()
        navigateToBack()
        observe()
    }

    private fun initIncomeCardAdapter() {
        incomeCardAdapter =
            IncomeCardAdapter(onMoveToIncomeDetailClick = {}, onClickReceiveSalary = {}, today = -1,
                countryCode = 0
            ).apply {
                submitList(getParcelable(
                    KEY_INCOME_CARD, IncomeCard::class.java
                )?.let { listOf(it.toIncomeCardEntity()) })
            }
    }

    private fun initIncomeWorkCheckAdapter(data: List<WorkCheckEntity?>) {
        val list = listOf(
            WorkCheckEntity(1, 9860, 1, "2024-02-05"),
            WorkCheckEntity(1, 9060, 2, "2024-02-10"),
            WorkCheckEntity(1, 6860, 3, "2024-02-12"),
            WorkCheckEntity(1, 1860, 4, "2024-02-01"),
            WorkCheckEntity(1, 2860, 5, "2024-02-02"),
            WorkCheckEntity(1, 8860, 6, "2024-03-05"),
            WorkCheckEntity(1, 6860, 7, "2024-03-10"),
            WorkCheckEntity(1, 3860, 1, "2024-03-12"),
            WorkCheckEntity(1, 5860, 1, "2024-03-01"),
            WorkCheckEntity(1, 1860, 1, "2024-03-02")
        )

        incomeWorkCheckAdapter = IncomeWorkCheckAdapter(onClickSortingBtn = { sortingDes ->
            val sortedList = if (sortingDes) sortedByDescending(data) else sortedByAscending(data)
            incomeWorkCheckAdapter.updateItem(sortedList)
        }).apply { submitList(sortedByDescending(data)) }
    }

    private fun observe() {
        viewModel.postIncomeDetail.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> initIncomeWorkCheckAdapter(it.data)
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun concatIncomeDetailAdapter() {
        binding.rvIncomeDetail.adapter = ConcatAdapter(incomeCardAdapter, incomeWorkCheckAdapter)
        setRecyclerViewItemDecoration()
    }

    private fun setRecyclerViewItemDecoration() {
        if (binding.rvIncomeDetail.itemDecorationCount == 0) {
            binding.rvIncomeDetail.addItemDecoration(
                IncomeDetailItemDecorator(requireContext()),
            )
        }
    }

    private fun sortedByAscending(list: List<WorkCheckEntity?>) = list.sortedBy { it?.workDay }

    private fun sortedByDescending(list: List<WorkCheckEntity?>) =
        list.sortedByDescending { it?.workDay }

    private fun navigateToBack() {
        binding.appbarIncomeDetail.ivAppbarBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}