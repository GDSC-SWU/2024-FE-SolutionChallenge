package com.teamfairy.feature.community

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.domain.entity.CommentEntity
import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.feature.R
import com.teamfairy.feature.communitydetail.CommunityDetailCommentAdapter
import com.teamfairy.feature.communitydetail.CommunityDetailFeedAdapter
import com.teamfairy.feature.databinding.FragmentCommunityShareDeliveryBinding
import com.teamfairy.feature.dialog.DeleteDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CommunityShareDeliveryFragment :
    BindingFragment<FragmentCommunityShareDeliveryBinding>(R.layout.fragment_community_share_delivery) {
    override fun initView() {
        initCommunityTabAdapter()
        concatCommunityDetailAdapter()
        observeClickBack()
    }

    private val viewModel by activityViewModels<CommunityViewModel>()
    private fun initCommunityTabAdapter() {
        val list = listOf(
            FeedEntity("Test1"),
            FeedEntity("Test2"),
            FeedEntity("Test3"),
            FeedEntity("Test4"),
            FeedEntity("Test5")
        )

        binding.rvCommunityShareDelivery.adapter =
            CommunityTabAdapter(onMoveToCommunityDetailClick = {
                setVisibleCommunityDetail(true)
                viewModel.stateOpenShareDetail(true)
            }
            ).apply {
                submitList(list)
            }
    }

    private fun concatCommunityDetailAdapter() {
        val communityDetailFeedAdapter = CommunityDetailFeedAdapter(onClickKebab = {
            initDeleteDialog("1")
        }).apply {
            submitList(listOf(FeedEntity("test1")))
        }

        val communityDetailCommentAdapter = CommunityDetailCommentAdapter(onClickKebab = {
            initDeleteDialog("1")
        }).apply {
            submitList(listOf(CommentEntity("test1"), CommentEntity("test2")))
        }

        binding.rvCommunityShareDeliveryDetail.adapter =
            ConcatAdapter(communityDetailFeedAdapter, communityDetailCommentAdapter)
    }


    private fun initDeleteDialog(id: String) {
        if (id == viewModel.getMemberId().toString()) {
            val dialog = DeleteDialog("Do you want to delete it?", 2)
            dialog.show(childFragmentManager, "delete")
        } else {
            val dialog = DeleteDialog("Do you want to delete it?", 3)
            dialog.show(childFragmentManager, "delete")
        }
    }

    private fun observeClickBack() {
        viewModel.stateClickShareBack.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                true -> {
                    setVisibleCommunityDetail(false)
                    viewModel.stateOpenShareDetail(false)
                }

                false -> {
                    setVisibleCommunityDetail(true)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setVisibleCommunityDetail(state: Boolean) {
        binding.rvCommunityShareDelivery.isVisible = !state
        binding.rvCommunityShareDeliveryDetail.isVisible = state
        binding.etCommunityShareDeliveryDetailInputComment.isVisible = state
    }
}