package com.teamfairy.feature.community

import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentCommunityDailyBinding

class CommunityDailyFragment : BindingFragment<FragmentCommunityDailyBinding>(R.layout.fragment_community_daily) {
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

        binding.rvCommunityDaily.adapter = CommunityTabAdapter(onMoveToCommunityDetailClick = {
        }
        ).apply {
            submitList(list)
        }
    }
}