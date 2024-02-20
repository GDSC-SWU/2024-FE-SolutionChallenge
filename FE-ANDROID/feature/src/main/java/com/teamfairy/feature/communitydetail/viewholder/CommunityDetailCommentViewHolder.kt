package com.teamfairy.feature.communitydetail.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.domain.entity.CommentEntity
import com.teamfairy.feature.databinding.ItemCommunityCommentBinding

class CommunityDetailCommentViewHolder(
    private val binding: ItemCommunityCommentBinding,
    private val onClickKebab: (CommentEntity) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: CommentEntity) {
        with(binding) {
            tvItemCommunityCommentNickname.text = data.nickname

            if (bindingAdapterPosition == 0) dividerItemCommunityComment.isVisible = false
            ivCommunityCommentKebab.setOnClickListener { onClickKebab(data) }
        }
    }
}