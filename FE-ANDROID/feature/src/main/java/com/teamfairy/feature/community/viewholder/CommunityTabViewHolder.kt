package com.teamfairy.feature.community.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.CommunityEntity
import com.teamfairy.feature.databinding.ItemCommunityBinding

class CommunityTabViewHolder(
    private val binding: ItemCommunityBinding,
    private val onMoveToCommunityDetailClick: (CommunityEntity) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: CommunityEntity) {
        with(binding) {
            tvItemCommunityTitle.text = data.title
        }
    }
}