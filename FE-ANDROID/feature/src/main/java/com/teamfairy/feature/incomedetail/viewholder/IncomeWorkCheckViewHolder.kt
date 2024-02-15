package com.teamfairy.feature.incomedetail.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.WorkCheckEntity
import com.teamfairy.feature.databinding.ItemIncomeDetailWorkCheckBinding
import timber.log.Timber

class IncomeWorkCheckViewHolder(
    private val binding: ItemIncomeDetailWorkCheckBinding,
    private val onClickSortingBtn: (Boolean) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: WorkCheckEntity) {
        binding.workCheck = data
        binding.executePendingBindings()
        if (bindingAdapterPosition == 0) setVisibleHeadItem()
        setOnkSortingBtnClickListener()
        setOnWorkCheckBtnClickListener()
    }

    private fun setVisibleHeadItem() = with(binding) {
        tvIncomeDetailWorkCheckTitle.isVisible = true
        tvIncomeDetailWorkCheckYear.isVisible = true
        tvIncomeDetailWorkCheckSorting.isVisible = true
    }

    private fun setOnkSortingBtnClickListener() {
        binding.tvIncomeDetailWorkCheckSorting.setOnClickListener {
            val sortingDes = binding.tvIncomeDetailWorkCheckSorting.text.toString() == "latest"
            Timber.tag("test").d(sortingDes.toString())
            Timber.tag("test").d(binding.tvIncomeDetailWorkCheckSorting.text.toString())
            if (sortingDes) {
                binding.tvIncomeDetailWorkCheckSorting.text = "oldest"
            } else {
                binding.tvIncomeDetailWorkCheckSorting.text = "latest"
            }
            onClickSortingBtn(sortingDes)
        }
    }

    private fun setOnWorkCheckBtnClickListener() {
        binding.ivIncomeDetailWorkCheck.setOnClickListener {
            binding.ivIncomeDetailWorkCheck.isSelected = !binding.ivIncomeDetailWorkCheck.isSelected
        }
    }
}