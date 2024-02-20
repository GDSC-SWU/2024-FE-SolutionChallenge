package com.teamfairy.feature.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamfairy.core_ui.view.UiState
import com.teamfairy.domain.entity.PostCommentEntity
import com.teamfairy.domain.entity.PostingFeedEntity
import com.teamfairy.domain.repository.CommunityRepository
import com.teamfairy.domain.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
    private val communityRepository: CommunityRepository
) : ViewModel() {

    private val _stateOpenDailyDetail = MutableStateFlow<Boolean>(false)
    val stateOpenDailyDetail: StateFlow<Boolean> get() = _stateOpenDailyDetail

    private val _stateClickDailyBack = MutableSharedFlow<Boolean>()
    val stateClickDailyBack: SharedFlow<Boolean> get() = _stateClickDailyBack

    fun stateOpenDailyDetail(state: Boolean) = viewModelScope.launch {
        _stateOpenDailyDetail.emit(state)
    }

    fun stateClickDailyBack(state: Boolean) = viewModelScope.launch {
        _stateClickDailyBack.emit(state)
    }

    private val _stateOpenShareDetail = MutableStateFlow<Boolean>(false)
    val stateOpenShareDetail: StateFlow<Boolean> get() = _stateOpenShareDetail

    private val _stateClickShareBack = MutableSharedFlow<Boolean>()
    val stateClickShareBack: SharedFlow<Boolean> get() = _stateClickShareBack

    fun stateOpenShareDetail(state: Boolean) = viewModelScope.launch {
        _stateOpenShareDetail.emit(state)
    }

    fun stateClickShareBack(state: Boolean) = viewModelScope.launch {
        _stateClickShareBack.emit(state)
    }

    fun getMemberId() = userInfoRepository.getMemberId()

    private val _postCommunityList = MutableStateFlow<UiState<List<Unit>>>(UiState.Empty)
    val postCommunityList: StateFlow<UiState<List<Unit>>> get() = _postCommunityList

    fun postCommunityList(request: PostingFeedEntity) = viewModelScope.launch {
        communityRepository.postCommunityList(request)
            .fold({ if (it != null) _postCommunityList.value = UiState.Success(it) },
                { UiState.Failure(it.message.toString()) })
    }

    fun postCommunityPosting(request: PostingFeedEntity) = viewModelScope.launch {
        communityRepository.postCommunityList(request)
            .fold({ }, { UiState.Failure(it.message.toString()) })
    }

    private val _postCommunityDetail = MutableStateFlow<UiState<List<Unit>>>(UiState.Empty)
    val postCommunityDetail: StateFlow<UiState<List<Unit>>> get() = _postCommunityDetail

    fun postCommunityDetail(tblCommunityId: Int) = viewModelScope.launch {
        communityRepository.postCommunityDetail(tblCommunityId)
            .fold({ if (it != null) _postCommunityDetail.value = UiState.Success(it) },
                { UiState.Failure(it.message.toString()) })
    }

    fun postCommentPosting(request: PostCommentEntity) = viewModelScope.launch {
        communityRepository.postComment(request)
            .fold({ }, { UiState.Failure(it.message.toString()) })
    }

    fun deleteComment(tblReplyId: Int) = viewModelScope.launch {
        communityRepository.deleteComment(tblReplyId)
            .fold({ }, { UiState.Failure(it.message.toString()) })
    }

    fun deleteFeed(tblCommunityId: Int) = viewModelScope.launch {
        communityRepository.deleteCommunityFeed(tblCommunityId)
            .fold({ }, { UiState.Failure(it.message.toString()) })
    }
}