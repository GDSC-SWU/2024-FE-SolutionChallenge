package com.teamfairy.feature.community.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.feature.databinding.ItemCommunityFeedBinding

class CommunityTabViewHolder(
    private val binding: ItemCommunityFeedBinding,
    private val onMoveToCommunityDetailClick: (FeedEntity) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: FeedEntity) {
        with(binding) {
            tvItemCommunityTitle.text = data.title
            root.setOnClickListener {
                onMoveToCommunityDetailClick(data)
            }
        }
    }
}