package com.teamfairy.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamfairy.core_ui.view.UiState
import com.teamfairy.domain.entity.AuthEntity
import com.teamfairy.domain.repository.SignInRepository
import com.teamfairy.domain.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository,
    private val userInfoRepository: UserInfoRepository
) : ViewModel() {
    private val _postSignIn = MutableStateFlow<UiState<AuthEntity>>(UiState.Empty)
    val postSignIn: StateFlow<UiState<AuthEntity>> = _postSignIn

    fun postSignIn(idToken: String) = viewModelScope.launch {
        signInRepository.postLogin(idToken).fold(
            { if (it != null) _postSignIn.value = UiState.Success(it) },
            { UiState.Failure(it.message.toString()) }
        )
    }

    fun getAccessToken() = userInfoRepository.getAccessToken()

    fun saveAccessToken(accessToken: String) = userInfoRepository.saveAccessToken(accessToken)

    fun getRefreshToken() = userInfoRepository.getRefreshToken()

    fun saveRefreshToken(refreshToken: String) = userInfoRepository.saveRefreshToken(refreshToken)

    fun getMemberId() = userInfoRepository.getMemberId()

    fun saveMemberId(memberId: Int) = userInfoRepository.saveMemberId(memberId)

    fun getNickName() = userInfoRepository.getNickName()

    fun saveNickName(nickName: String) = userInfoRepository.saveNickName(nickName)

    fun checkLogin() = userInfoRepository.checkLogin()

    fun saveCheckLogin(checkLogin: Boolean) = userInfoRepository.saveCheckLogin(checkLogin)

    fun getMemberProfileUrl() = userInfoRepository.getMemberProfileUrl()

    fun saveMemberProfileUrl(memberUrl: String) = userInfoRepository.saveMemberProfileUrl(memberUrl)
}