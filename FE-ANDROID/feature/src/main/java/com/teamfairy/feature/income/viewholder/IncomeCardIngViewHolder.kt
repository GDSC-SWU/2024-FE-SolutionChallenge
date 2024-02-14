package com.teamfairy.feature.income.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.feature.databinding.ItemIncomeCardIngBinding

class IncomeCardIngViewHolder(
    private val binding: ItemIncomeCardIngBinding,
    private val onMoveToIncomeDetailClick: (IncomeCardEntity) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: IncomeCardEntity) {
        with(binding) {
            income = data
            executePendingBindings()

            root.setOnClickListener { onMoveToIncomeDetailClick(data) }
        }
    }
}