package com.teamfairy.feature.income.viewholder

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.feature.databinding.ItemIncomeCardIngBinding
import com.teamfairy.feature.util.changePriceFormat
import com.teamfairy.feature.util.exchange

class IncomeCardIngViewHolder(
    private val binding: ItemIncomeCardIngBinding,
    private val onMoveToIncomeDetailClick: (IncomeCardEntity) -> Unit,
    private val countryCode: Int
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: IncomeCardEntity) {
        with(binding) {
            income = data
            executePendingBindings()

            if (data.incomeId == -1) {
                binding.tvIncomeCardSalaryEmpty.isVisible = true
                binding.tvIncomeCardSalary.visibility = View.INVISIBLE
                binding.switchItemIncomeCard.isVisible = false
            }else{
                root.setOnClickListener { onMoveToIncomeDetailClick(data) }
            }
            binding.switchItemIncomeCard.setOnClickListener {
                if (binding.switchItemIncomeCard.isChecked) {
                    binding.tvIncomeCardSalary.exchange(data.currentSalary, countryCode.toString())
                } else {
                    binding.tvIncomeCardSalary.changePriceFormat(data.currentSalary)
                }
            }
        }
    }
}