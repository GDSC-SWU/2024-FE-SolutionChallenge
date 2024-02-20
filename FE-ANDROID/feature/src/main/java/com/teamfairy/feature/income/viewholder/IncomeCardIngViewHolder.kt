package com.teamfairy.feature.income.viewholder

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

            root.setOnClickListener { onMoveToIncomeDetailClick(data) }
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