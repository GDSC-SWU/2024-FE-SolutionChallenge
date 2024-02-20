package com.teamfairy.feature.communitydetail.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.feature.databinding.ItemCommunityDetailFeedBinding

class CommunityDetailFeedViewHolder(
    private val binding: ItemCommunityDetailFeedBinding,
    private val onClickKebab: (FeedEntity) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: FeedEntity) {
        with(binding) {
            tvItemCommunityDetailFeedTitle.text = data.title
        }
    }
}