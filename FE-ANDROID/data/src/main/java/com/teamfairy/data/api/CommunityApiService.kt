package com.teamfairy.data.api

import com.teamfairy.data.dto.response.ResponseIncomeListDto
import retrofit2.http.POST

interface CommunityApiService {
    @POST("/api/v1/income/list")
    suspend fun postIncomeList(
    ): List<ResponseIncomeListDto>

}