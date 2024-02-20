package com.teamfairy.data.api

import com.teamfairy.data.dto.BaseResponse
import com.teamfairy.data.dto.request.RequestIncomeAddDto
import com.teamfairy.data.dto.response.IncomeDetailDto
import com.teamfairy.data.dto.response.ResponseAddIncomeDto
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
    ): ResponseAddIncomeDto

    @GET("/api/v1/income/detail")
    suspend fun postIncomeDetail(
        @Query("tblIncomeId") tblincomeId: Int,
        @Query("orderType") orderType: String
    ): IncomeDetailDto

    @POST("/api/v1/income/delete/{tblincomeId}")
    suspend fun deleteIncome(
        @Path(value = "tblincomeId") tblincomeId: Int
    ): String
}

