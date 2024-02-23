package com.teamfairy.feature.community.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.feature.databinding.ItemCommunityFeedBinding

class CommunityTabViewHolder(
    private val binding: ItemCommunityFeedBinding,
    private val onMoveToCommunityDetailClick: (FeedEntity) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: FeedEntity) {
        with(binding) {
            when(adapterPosition){
                0 -> ivItemCommunityImg.load("https://github.com/TeamDon-tBe/ANDROID/assets/98076050/7f4dd533-7b65-47fe-b351-80db730d49f4")
                3 -> ivItemCommunityImg.load("https://github.com/TeamDon-tBe/ANDROID/assets/98076050/97d0c784-39c3-409a-974a-50ede3b757fb")
            }
            if(adapterPosition == 0){

            }
            binding.data = data
            executePendingBindings()
            tvItemCommunityTitle.text = data.title
            root.setOnClickListener {
                onMoveToCommunityDetailClick(data)
            }
        }
    }
}

