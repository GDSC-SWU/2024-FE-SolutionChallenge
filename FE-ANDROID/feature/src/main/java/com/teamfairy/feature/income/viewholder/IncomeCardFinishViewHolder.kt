package com.teamfairy.feature.income.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.feature.databinding.ItemIncomeCardFinishBinding

class IncomeCardFinishViewHolder(
    private val binding: ItemIncomeCardFinishBinding,
    private val onClickReceiveSalary: (Boolean) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: IncomeCardEntity) {
        with(binding) {
            income = data
            executePendingBindings()

            tvItemIncomeCardFinishYes.setOnClickListener { onClickReceiveSalary(true) }
            tvItemIncomeCardFinishNo.setOnClickListener { onClickReceiveSalary(false) }
        }
    }
}