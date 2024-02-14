package com.teamfairy.feature.community

import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.domain.entity.CommunityEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentCommunityShareDeliveryBinding

class CommunityShareDeliveryFragment : BindingFragment<FragmentCommunityShareDeliveryBinding>(R.layout.fragment_community_share_delivery) {
    override fun initView() {
        initCommunityTabAdapter()
    }

    private fun initCommunityTabAdapter() {
        val list = listOf(
            CommunityEntity("Test1"),
            CommunityEntity("Test2"),
            CommunityEntity("Test3"),
            CommunityEntity("Test4"),
            CommunityEntity("Test5")
        )

        binding.rvCommunityShareDelivery.adapter = CommunityTabAdapter(onMoveToCommunityDetailClick = {
        }
        ).apply {
            submitList(list)
        }
    }
}