package com.teamfairy.feature.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.teamfairy.core_ui.view.ItemDiffCallback
import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.feature.community.viewholder.CommunityTabViewHolder
import com.teamfairy.feature.databinding.ItemCommunityFeedBinding

class CommunityTabAdapter(
    private val onMoveToCommunityDetailClick: (FeedEntity) -> Unit
) : ListAdapter<FeedEntity, CommunityTabViewHolder>(
    CommunityTabDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabViewHolder {
        val binding =
            ItemCommunityFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityTabViewHolder(binding, onMoveToCommunityDetailClick)
    }

    override fun onBindViewHolder(holder: CommunityTabViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val CommunityTabDiffCallback =
            ItemDiffCallback<FeedEntity>(onItemsTheSame = { old, new -> old.title == new.title },
                onContentsTheSame = { old, new -> old == new })
    }
}