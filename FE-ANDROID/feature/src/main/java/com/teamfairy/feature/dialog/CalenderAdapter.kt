package com.teamfairy.feature.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.teamfairy.core_ui.view.ItemDiffCallback
import com.teamfairy.feature.databinding.ItemCalenderDateBinding
import com.teamfairy.feature.dialog.viewholder.CalenderViewHolder

class CalenderAdapter(
    private val onDateClick: (String) -> Unit
) : ListAdapter<String, CalenderViewHolder>(
    ArticleAllDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        val binding =
            ItemCalenderDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalenderViewHolder(binding, onDateClick)
    }

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        private val ArticleAllDiffCallback =
            ItemDiffCallback<String>(onItemsTheSame = { old, new -> old == new },
                onContentsTheSame = { old, new -> old == new })
    }
}