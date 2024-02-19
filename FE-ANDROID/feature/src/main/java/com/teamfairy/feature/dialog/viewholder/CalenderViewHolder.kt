package com.teamfairy.feature.dialog.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.feature.databinding.ItemCalenderDateBinding

class CalenderViewHolder(
    private val binding: ItemCalenderDateBinding,
    private val onDateClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: String) {
        binding.tvCalenderDate.text = data
        binding.tvCalenderDate.setOnClickListener {
            binding.tvCalenderDate.isSelected = !binding.tvCalenderDate.isSelected
            onDateClick(binding.tvCalenderDate.text.toString())
        }
    }
}
