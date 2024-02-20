package com.teamfairy.data.api

import com.teamfairy.data.dto.BaseResponse
import com.teamfairy.data.dto.request.RequestIncomeAddDto
import com.teamfairy.data.dto.response.ResponseIncomeListDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IncomeApiService {
    @GET("/api/v1/income/list")
    suspend fun postIncomeList(
    ): BaseResponse<List<ResponseIncomeListDto>>

    @POST("/api/v1/income/create")
    suspend fun postAddIncome(
        @Body request: RequestIncomeAddDto
    ): ResponseIncomeListDto

    @GET("/api/v1/income/detail")
    suspend fun postIncomeDetail(
        @Query("tblincomeId") tblincomeId: Int,
        @Query("orderType") orderType: String
    ): ResponseIncomeListDto

    @POST("/api/v1/income/delete/{tblincomeId}")
    suspend fun deleteIncome(
        @Path(value = "tblincomeId") tblincomeId: Int
    ): String
}

