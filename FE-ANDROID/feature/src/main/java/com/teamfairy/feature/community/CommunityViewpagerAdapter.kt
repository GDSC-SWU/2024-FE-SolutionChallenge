package com.teamfairy.feature.community

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CommunityViewpagerAdapter(fragment: CommunityFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CommunityDailyFragment()
            else -> CommunityShareDeliveryFragment()
        }
    }
}