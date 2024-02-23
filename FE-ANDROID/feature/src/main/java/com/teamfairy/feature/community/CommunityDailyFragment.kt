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
import com.teamfairy.feature.databinding.FragmentCommunityDailyBinding
import com.teamfairy.feature.dialog.DeleteDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CommunityDailyFragment :
    BindingFragment<FragmentCommunityDailyBinding>(R.layout.fragment_community_daily) {
    private lateinit var communityDetailFeedAdapter: CommunityDetailFeedAdapter
    private lateinit var communityDetailCommentAdapter: CommunityDetailCommentAdapter
    private val list = listOf(
        CommentEntity(
            1,
            "fairy",
            "30 min ago",
            "Check out the wage deferral manual on the Income tab! It'll help you with the details."
        ),
        CommentEntity(
            1,
            "cattt",
            "10 min ago",
            "I'm also coming in late for a day or two, but it's so frustrating. Still, talking like this gives me a little comfort."
        )
    )

    private val viewModel by activityViewModels<CommunityViewModel>()
    override fun initView() {
        viewModel.postCommunityList("DAILY")
        concatCommunityDetailAdapter()
        observeClickBack()
        observe()
        initCommentAdapter(list)
        binding.etCommunityDailyDetailInputComment.setOnClickListener {

        }
    }

    private fun observe() {
        viewModel.postCommunityDetail.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> initCommentAdapter(list)
                else -> Unit
            }
        }.launchIn(lifecycleScope)

        viewModel.postCommunityList.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> initCommunityTabAdapter(it.data)
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun initCommunityTabAdapter(data: List<FeedEntity>) {
        binding.rvCommunityDaily.adapter = CommunityTabAdapter(onMoveToCommunityDetailClick = {
            setVisibleCommunityDetail(true)
            viewModel.stateOpenDailyDetail(true)
            initFeedDetail(it)
            //viewModel.postCommunityDetail(it.contentId)
        }
        ).apply {
            submitList(data)
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
            binding.rvCommunityDailyDetail.adapter =
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
        viewModel.stateClickDailyBack.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                true -> {
                    setVisibleCommunityDetail(false)
                    viewModel.stateOpenDailyDetail(false)
                }

                false -> {
                    setVisibleCommunityDetail(true)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setVisibleCommunityDetail(state: Boolean) {
        binding.rvCommunityDaily.isVisible = !state
        binding.rvCommunityDailyDetail.isVisible = state
        binding.etCommunityDailyDetailInputComment.isVisible = state
    }
}