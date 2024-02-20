package com.teamfairy.feature.income

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.core_ui.view.ItemDiffCallback
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.feature.databinding.ItemIncomeCardFinishBinding
import com.teamfairy.feature.databinding.ItemIncomeCardIngBinding
import com.teamfairy.feature.income.viewholder.IncomeCardFinishViewHolder
import com.teamfairy.feature.income.viewholder.IncomeCardIngViewHolder

class IncomeCardAdapter(
    private val onMoveToIncomeDetailClick: (IncomeCardEntity) -> Unit,
    private val onClickReceiveSalary: (Boolean, Int) -> Unit,
    private val today: Int,
    private val countryCode: Int
) : ListAdapter<IncomeCardEntity, RecyclerView.ViewHolder>(
    IncomeCardDiffCallback
) {

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int {
        return when {
            today < currentList[position].payDay?.toInt() ?: 0 -> PAY_DAY
            else -> ING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PAY_DAY -> {
                val binding = ItemIncomeCardFinishBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                IncomeCardFinishViewHolder(binding, onClickReceiveSalary)
            }

            else -> {
                val binding = ItemIncomeCardIngBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                IncomeCardIngViewHolder(binding, onMoveToIncomeDetailClick, countryCode)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (today) {
            currentList[position].payDay?.toInt() -> (holder as IncomeCardFinishViewHolder).run {
                bind(currentList[position])
            }

            else -> (holder as IncomeCardIngViewHolder).run {
                bind(currentList[position])
            }
        }
    }

    companion object {
        val IncomeCardDiffCallback =
            ItemDiffCallback<IncomeCardEntity>(onItemsTheSame = { old, new -> old.incomeId == new.incomeId },
                onContentsTheSame = { old, new -> old == new })

        const val PAY_DAY = 0
        const val ING = 2
    }
}