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
import com.teamfairy.feature.databinding.FragmentCommunityDailyBinding
import com.teamfairy.feature.dialog.DeleteDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CommunityDailyFragment :
    BindingFragment<FragmentCommunityDailyBinding>(R.layout.fragment_community_daily) {

    private val viewModel by activityViewModels<CommunityViewModel>()
    override fun initView() {
        initCommunityTabAdapter()
        concatCommunityDetailAdapter()
        observeClickBack()
    }

    private fun initCommunityTabAdapter() {
        val list = listOf(
            FeedEntity("Test1"),
            FeedEntity("Test2"),
            FeedEntity("Test3"),
            FeedEntity("Test4"),
            FeedEntity("Test5"),
            FeedEntity("Test1"),
            FeedEntity("Test2"),
            FeedEntity("Test3"),
            FeedEntity("Test4"),
            FeedEntity("Test5")
        )

        binding.rvCommunityDaily.adapter = CommunityTabAdapter(onMoveToCommunityDetailClick = {
            setVisibleCommunityDetail(true)
            viewModel.stateOpenDailyDetail(true)
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

        val communityDetailCommentAdapter = CommunityDetailCommentAdapter(onClickKebab ={
            initDeleteDialog("1")
        }).apply {
            submitList(
                listOf(
                    CommentEntity("test1"),
                    CommentEntity("test2"),
                    CommentEntity("test1"),
                    CommentEntity("test2"),
                    CommentEntity("test1"),
                    CommentEntity("test2")
                )
            )
        }

        binding.rvCommunityDailyDetail.adapter =
            ConcatAdapter(communityDetailFeedAdapter, communityDetailCommentAdapter)
    }

    private fun initDeleteDialog(id:String){
        if(id == viewModel.getMemberId().toString()){
            val dialog = DeleteDialog("Do you want to delete it?",2 )
            dialog.show(childFragmentManager, "delete")
        }else{
            val dialog = DeleteDialog("Do you want to report it?",3 )
            dialog.show(childFragmentManager, "delete")
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