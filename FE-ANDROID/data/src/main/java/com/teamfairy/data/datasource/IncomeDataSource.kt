package com.teamfairy.data.datasource

import com.teamfairy.data.dto.BaseResponse
import com.teamfairy.data.dto.request.RequestIncomeAddDto
import com.teamfairy.data.dto.response.ResponseIncomeListDto

interface IncomeDataSource {
    suspend fun postIncomeList(): BaseResponse<List<ResponseIncomeListDto>>

    suspend fun postAddIncome(request: RequestIncomeAddDto): ResponseIncomeListDto
    suspend fun postIncomeDetail(incomeId: Int, oderType: String): ResponseIncomeListDto

    suspend fun deleteIncome(incomeId: Int): String
}