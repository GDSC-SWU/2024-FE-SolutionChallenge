package com.teamfairy.feature.incomedetail.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.WorkCheckEntity
import com.teamfairy.feature.databinding.ItemIncomeDetailWorkCheckBinding

class IncomeWorkCheckViewHolder(
    private val binding: ItemIncomeDetailWorkCheckBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: WorkCheckEntity) {
        binding.workCheck = data
        binding.executePendingBindings()
        if (bindingAdapterPosition == 0) setVisibleHeadItem()
    }

    private fun setVisibleHeadItem() = with(binding) {
        tvIncomeDetailWorkCheckTitle.isVisible = true
        tvIncomeDetailWorkCheckYear.isVisible = true
        tvIncomeDetailWorkCheckFilter.isVisible = true
    }
}