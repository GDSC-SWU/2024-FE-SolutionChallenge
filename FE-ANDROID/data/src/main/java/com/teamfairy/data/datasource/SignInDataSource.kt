package com.teamfairy.data.datasource

import com.teamfairy.data.dto.BaseResponse
import com.teamfairy.data.dto.response.ResponseSignInDto

interface SignInDataSource {
    suspend fun postSignIn(idToken: String): ResponseSignInDto
}