package com.teamfairy.feature.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamfairy.core_ui.view.UiState
import com.teamfairy.domain.entity.AddIncomeEntity
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.domain.entity.WorkCheckEntity
import com.teamfairy.domain.repository.IncomeRepository
import com.teamfairy.domain.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(
    private val incomeRepository: IncomeRepository,
    private val userInfoRepository: UserInfoRepository
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

    private val _postIncomeList = MutableStateFlow<UiState<List<IncomeCardEntity?>>>(UiState.Empty)
    val postIncomeList: StateFlow<UiState<List<IncomeCardEntity?>>> = _postIncomeList

    private val _postAddIncome = MutableStateFlow<UiState<Int>>(UiState.Empty)
    val postAddIncome: StateFlow<UiState<Int>> = _postAddIncome

    private val _postIncomeDetail =
        MutableStateFlow<UiState<List<WorkCheckEntity?>>>(UiState.Empty)
    val postIncomeDetail: StateFlow<UiState<List<WorkCheckEntity?>>> = _postIncomeDetail

    fun postIncomeList() = viewModelScope.launch {
        incomeRepository.postIncomeList()
            .fold({ if (it != null) _postIncomeList.value = UiState.Success(it) },
                { UiState.Failure(it.message.toString())
                   })
    }

    fun postAddIncome(requestBody: AddIncomeEntity) = viewModelScope.launch {
        incomeRepository.postAddIncome(requestBody)
            .fold({ if (it != null) _postAddIncome.value = UiState.Success(it)
                Timber.tag("tttt").d(it.toString())},
                { UiState.Failure(it.message.toString())
                    Timber.tag("tttt").d(it.message.toString())
                })
    }

    fun postIncomeDetail(incomeId: Int) = viewModelScope.launch {
        incomeRepository.postIncomeDetail(incomeId, "DESC")
            .fold({ if (it != null) _postIncomeDetail.value = UiState.Success(it)
                Timber.tag("tttt").d(it.toString()) },
                { UiState.Failure(it.message.toString())
                    Timber.tag("tttt").d(it.message.toString())}
            )
    }

    fun deleteIncome(id: Int) = viewModelScope.launch {
        incomeRepository.deleteIncome(id)
    }

    fun getNationality() = userInfoRepository.getNationality()
}