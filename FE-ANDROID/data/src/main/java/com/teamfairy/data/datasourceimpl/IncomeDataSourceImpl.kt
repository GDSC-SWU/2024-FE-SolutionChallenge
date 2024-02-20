package com.teamfairy.data.datasourceimpl

import com.teamfairy.data.api.IncomeApiService
import com.teamfairy.data.datasource.IncomeDataSource
import com.teamfairy.data.dto.BaseResponse
import com.teamfairy.data.dto.request.RequestIncomeAddDto
import com.teamfairy.data.dto.response.IncomeDetailDto
import com.teamfairy.data.dto.response.ResponseAddIncomeDto
import com.teamfairy.data.dto.response.ResponseIncomeListDto
import javax.inject.Inject

class IncomeDataSourceImpl @Inject constructor(
    private val apiService: IncomeApiService
) : IncomeDataSource {
    override suspend fun postIncomeList(): BaseResponse<List<ResponseIncomeListDto>> {
        return apiService.postIncomeList()
    }

    override suspend fun postAddIncome(requestIncomeAddDto: RequestIncomeAddDto): ResponseAddIncomeDto {
        return apiService.postAddIncome(requestIncomeAddDto)
    }

    override suspend fun postIncomeDetail(
        incomeId: Int,
        oderType: String
    ): IncomeDetailDto {
        return apiService.postIncomeDetail(incomeId, oderType)
    }

    override suspend fun deleteIncome(incomeId: Int): String {
        return apiService.deleteIncome(incomeId)
    }
}