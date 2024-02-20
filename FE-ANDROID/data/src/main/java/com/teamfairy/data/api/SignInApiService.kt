package com.teamfairy.data.api

import com.teamfairy.data.dto.request.RequestNationalityDto
import com.teamfairy.data.dto.response.ResponseNationalityDto
import com.teamfairy.data.dto.response.ResponseSignInDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SignInApiService {
    @POST("/oauth2/authorization/google")
    suspend fun postSignIn(
        @Header("id-token") idToken: String
    ): ResponseSignInDto

    @POST("/api/v1/member/update")
    suspend fun postNationality(
        @Body request: RequestNationalityDto
    ): ResponseNationalityDto
}