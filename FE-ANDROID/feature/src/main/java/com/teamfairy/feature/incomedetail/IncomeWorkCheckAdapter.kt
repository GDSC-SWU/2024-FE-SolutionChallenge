package com.teamfairy.feature.incomedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.teamfairy.core_ui.view.ItemDiffCallback
import com.teamfairy.domain.entity.WorkCheckEntity
import com.teamfairy.feature.databinding.ItemIncomeDetailWorkCheckBinding
import com.teamfairy.feature.incomedetail.viewholder.IncomeWorkCheckViewHolder

class IncomeWorkCheckAdapter(
    private val onClickSortingBtn: (Boolean) -> Unit,
) : ListAdapter<WorkCheckEntity, IncomeWorkCheckViewHolder>(
    IncomeWorkCheckDiffCallback
) {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): IncomeWorkCheckViewHolder {
        val binding = ItemIncomeDetailWorkCheckBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return IncomeWorkCheckViewHolder(binding, onClickSortingBtn)
    }

    fun updateItem(data:List<WorkCheckEntity?>){
        submitList(data)
    }

    override fun onBindViewHolder(holder: IncomeWorkCheckViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val IncomeWorkCheckDiffCallback =
            ItemDiffCallback<WorkCheckEntity>(onItemsTheSame = { old, new -> old.workCheckId == new.workCheckId },
                onContentsTheSame = { old, new -> old == new })
    }
}