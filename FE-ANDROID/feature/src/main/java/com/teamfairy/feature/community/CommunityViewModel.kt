package com.teamfairy.feature.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
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
}