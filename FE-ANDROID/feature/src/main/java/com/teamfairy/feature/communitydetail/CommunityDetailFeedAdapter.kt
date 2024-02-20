package com.teamfairy.feature.communitydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.feature.community.CommunityTabAdapter.Companion.CommunityTabDiffCallback
import com.teamfairy.feature.communitydetail.viewholder.CommunityDetailFeedViewHolder
import com.teamfairy.feature.databinding.ItemCommunityDetailFeedBinding

class CommunityDetailFeedAdapter(private val onClickKebab: (FeedEntity) -> Unit) :
    ListAdapter<FeedEntity, CommunityDetailFeedViewHolder>(
        CommunityTabDiffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CommunityDetailFeedViewHolder {
        val binding = ItemCommunityDetailFeedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CommunityDetailFeedViewHolder(binding, onClickKebab)
    }

    override fun onBindViewHolder(holder: CommunityDetailFeedViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}