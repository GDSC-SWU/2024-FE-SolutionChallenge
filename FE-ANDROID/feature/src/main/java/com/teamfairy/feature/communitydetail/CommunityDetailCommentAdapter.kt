package com.teamfairy.feature.communitydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.teamfairy.core_ui.view.ItemDiffCallback
import com.teamfairy.domain.entity.CommentEntity
import com.teamfairy.feature.communitydetail.viewholder.CommunityDetailCommentViewHolder
import com.teamfairy.feature.databinding.ItemCommunityCommentBinding

class CommunityDetailCommentAdapter(private val onClickKebab: (CommentEntity) -> Unit) :
    ListAdapter<CommentEntity, CommunityDetailCommentViewHolder>(
        CommunityDetailCommentDiffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommunityDetailCommentViewHolder {
        val binding =
            ItemCommunityCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityDetailCommentViewHolder(binding, onClickKebab)
    }

    override fun onBindViewHolder(holder: CommunityDetailCommentViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val CommunityDetailCommentDiffCallback =
            ItemDiffCallback<CommentEntity>(onItemsTheSame = { old, new -> old.nickname == new.nickname },
                onContentsTheSame = { old, new -> old == new })
    }
}