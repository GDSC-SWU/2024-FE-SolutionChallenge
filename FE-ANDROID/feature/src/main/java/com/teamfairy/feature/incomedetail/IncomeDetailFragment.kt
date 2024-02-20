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
        navigateToBack()
        observe()
    }

    private fun initIncomeCardAdapter() {
        incomeCardAdapter =
            IncomeCardAdapter(onMoveToIncomeDetailClick = {}, onClickReceiveSalary = {_,_,_->}, today = -1,
                countryCode = 0
            ).apply {
                submitList(getParcelable(
                    KEY_INCOME_CARD, IncomeCard::class.java
                )?.let { listOf(it.toIncomeCardEntity()) })
            }

        concatIncomeDetailAdapter()
    }

    private fun initIncomeWorkCheckAdapter(data: List<WorkCheckEntity?>) {
        incomeWorkCheckAdapter = IncomeWorkCheckAdapter(onClickSortingBtn = { sortingDes ->
            val sortedList = if (sortingDes) sortedByDescending(data) else sortedByAscending(data)
            incomeWorkCheckAdapter.updateItem(sortedList)
        }).apply { submitList(sortedByDescending(data)) }
    }

    private fun observe() {
        viewModel.postIncomeDetail.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    initIncomeWorkCheckAdapter(it.data)
                    concatIncomeDetailAdapter()
                }
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun concatIncomeDetailAdapter() {
        if(::incomeCardAdapter.isInitialized && ::incomeWorkCheckAdapter.isInitialized){
            binding.rvIncomeDetail.adapter = ConcatAdapter(incomeCardAdapter, incomeWorkCheckAdapter)
            setRecyclerViewItemDecoration()
        }
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