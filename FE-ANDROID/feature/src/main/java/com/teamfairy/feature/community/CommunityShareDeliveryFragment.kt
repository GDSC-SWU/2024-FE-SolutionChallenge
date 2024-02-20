package com.teamfairy.feature.community

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.teamfairy.core_ui.base.BindingFragment
import com.teamfairy.core_ui.view.UiState
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
    private lateinit var communityDetailFeedAdapter: CommunityDetailFeedAdapter
    private lateinit var communityDetailCommentAdapter: CommunityDetailCommentAdapter

    private val viewModel by activityViewModels<CommunityViewModel>()

    override fun initView() {
        initCommunityTabAdapter()
        concatCommunityDetailAdapter()
        observeClickBack()
        observe()
    }

    private fun observe() {
        viewModel.postCommunitySharedDetail.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> initCommentAdapter(emptyList())
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun initCommunityTabAdapter() {
        val list = listOf(
            FeedEntity(1, "test1111", "so", "10", "sdfjss"),
            FeedEntity(2, "test12221", "so", "10", "sdfjss"),
            FeedEntity(3, "test333", "so", "10", "sdfjss"),
            FeedEntity(4, "test1445", "so", "10", "sdfjss"),
            FeedEntity(5, "test14441", "so", "10", "sdfjss"),
        )

        binding.rvCommunityShareDelivery.adapter =
            CommunityTabAdapter(onMoveToCommunityDetailClick = {
                setVisibleCommunityDetail(true)
                viewModel.stateOpenShareDetail(true)
                initFeedDetail(it)
            }
            ).apply {
                submitList(list)
            }
    }

    private fun initFeedDetail(data: FeedEntity) {
        communityDetailFeedAdapter = CommunityDetailFeedAdapter(onClickKebab = {
            initDeleteDialog("1", it.contentId, "feed")
        }).apply {
            submitList(listOf(data))
        }
        concatCommunityDetailAdapter()
    }

    private fun initCommentAdapter(commentEntity: List<CommentEntity>) {
        communityDetailCommentAdapter = CommunityDetailCommentAdapter(onClickKebab = {
            initDeleteDialog("1", it.commentId, "comment")
        }).apply {
            submitList(
                commentEntity
            )
        }
        concatCommunityDetailAdapter()
    }

    private fun concatCommunityDetailAdapter() {
        if (::communityDetailFeedAdapter.isInitialized && ::communityDetailCommentAdapter.isInitialized)
            binding.rvCommunityShareDeliveryDetail.adapter =
                ConcatAdapter(communityDetailFeedAdapter, communityDetailCommentAdapter)
    }


    private fun initDeleteDialog(memberId: String, contentId: Int, type: String) {
        if (memberId == viewModel.getMemberId().toString()) {
            val dialog = DeleteDialog("Do you want to delete it?", 2, contentId)
            dialog.show(childFragmentManager, type)
        } else {
            val dialog = DeleteDialog("Do you want to report it?", 3, contentId)
            dialog.show(childFragmentManager, "report")
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