package com.teamfairy.data.api

import com.teamfairy.data.dto.BaseResponse
import com.teamfairy.data.dto.response.ResponseSignInDto
import retrofit2.http.Header
import retrofit2.http.POST

interface SignInApiService {
    @POST("/oauth2/authorization/google")
    suspend fun postSignIn(
        @Header("id-token") idToken: String
    ): BaseResponse<ResponseSignInDto>
}