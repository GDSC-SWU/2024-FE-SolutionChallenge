package com.teamfairy.feature.income.viewholder

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.feature.databinding.ItemIncomeCardIngBinding
import com.teamfairy.feature.util.changePriceFormat
import com.teamfairy.feature.util.exchange
import java.time.LocalDate

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
                binding.tvItemIncomeCardTime.isVisible = false
            } else {
                root.setOnClickListener { onMoveToIncomeDetailClick(data) }
            }

            val currentDate = LocalDate.now()

            val year = currentDate.year
            val month = currentDate.monthValue

            val formattedDate = "$year-${String.format("%02d", month)}-01 ~ ${data.payDay}"

            if (data.startDay == "-1") {
                binding.tvItemIncomeCardTime.text = formattedDate
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