package com.teamfairy.feature.community

import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentCommunityShareDeliveryBinding

class CommunityShareDeliveryFragment : BindingFragment<FragmentCommunityShareDeliveryBinding>(R.layout.fragment_community_share_delivery) {
    override fun initView() {
        initCommunityTabAdapter()
    }

    private fun initCommunityTabAdapter() {
        val list = listOf(
            FeedEntity("Test1"),
            FeedEntity("Test2"),
            FeedEntity("Test3"),
            FeedEntity("Test4"),
            FeedEntity("Test5")
        )

        binding.rvCommunityShareDelivery.adapter = CommunityTabAdapter(onMoveToCommunityDetailClick = {
        }
        ).apply {
            submitList(list)
        }
    }
}