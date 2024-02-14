package com.teamfairy.feature.community

import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.domain.entity.CommunityEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentCommunityDailyBinding

class CommunityDailyFragment : BindingFragment<FragmentCommunityDailyBinding>(R.layout.fragment_community_daily) {
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

        binding.rvCommunityDaily.adapter = CommunityTabAdapter(onMoveToCommunityDetailClick = {
        }
        ).apply {
            submitList(list)
        }
    }
}