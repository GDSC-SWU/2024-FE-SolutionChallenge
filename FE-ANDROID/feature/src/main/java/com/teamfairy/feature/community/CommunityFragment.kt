package com.teamfairy.feature.community

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.feature.R
import com.teamfairy.feature.databinding.FragmentCommunityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CommunityFragment : BindingFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private val viewModel by activityViewModels<CommunityViewModel>()

    override fun initView() {
        initCommunityTabAdapter()
        initNavigateToPostingBtnClickListener()
    }

    private fun initCommunityTabAdapter() {
        binding.vpCommunity.adapter = CommunityViewpagerAdapter(this)
        binding.vpCommunity.registerOnPageChangeCallback(pageChangeCallback)
        TabLayoutMediator(binding.tablayoutCommunity, binding.vpCommunity) { tab, position ->
            when (position) {
                0 -> tab.text = "daily"
                else -> tab.text = "share delivery"

            }
        }.attach()
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int,
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            if (position == 0) {
                observeOpenDailyDetail()
                binding.ivAppbarBack.setOnClickListener {
                    viewModel.stateClickDailyBack(true)
                }
            } else {
                observeOpenShareDetail()
                binding.ivAppbarBack.setOnClickListener {
                    viewModel.stateClickShareBack(true)
                }
            }
        }
    }

    private fun observeOpenDailyDetail() {
        viewModel.stateOpenDailyDetail.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                true -> {
                    binding.ivAppbarBack.isVisible = true
                }

                false -> {
                    binding.ivAppbarBack.isVisible = false
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun observeOpenShareDetail() {
        viewModel.stateOpenShareDetail.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                true -> {
                    binding.ivAppbarBack.isVisible = true
                }

                false -> {
                    binding.ivAppbarBack.isVisible = false
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun initNavigateToPostingBtnClickListener() {
        binding.ivCommunityFragmentPosting.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_community_to_communityPosting)
        }
    }
}