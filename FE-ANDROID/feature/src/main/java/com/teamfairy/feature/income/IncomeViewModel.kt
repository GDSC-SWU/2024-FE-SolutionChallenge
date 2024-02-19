package com.teamfairy.feature.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(
) : ViewModel() {

    private val _selectPayday = MutableSharedFlow<String>()
    val selectPayday: SharedFlow<String> get() = _selectPayday

    private val _selectSalaryType = MutableSharedFlow<String>()
    val selectSalaryType: SharedFlow<String> get() = _selectSalaryType

    fun selectPayday(day: String) = viewModelScope.launch {
        _selectPayday.emit(day)
    }

    fun selectSalaryType(type: String) = viewModelScope.launch {
        _selectSalaryType.emit(type)
    }
}