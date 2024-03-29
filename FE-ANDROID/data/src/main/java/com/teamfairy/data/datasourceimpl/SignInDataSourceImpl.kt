package com.teamfairy.data.datasourceimpl

import com.teamfairy.data.api.SignInApiService
import com.teamfairy.data.datasource.SignInDataSource
import com.teamfairy.data.dto.BaseResponse
import com.teamfairy.data.dto.request.RequestNationalityDto
import com.teamfairy.data.dto.response.ResponseNationalityDto
import com.teamfairy.data.dto.response.ResponseSignInDto
import javax.inject.Inject

class SignInDataSourceImpl @Inject constructor(
    private val apiService: SignInApiService
) : SignInDataSource {
    override suspend fun postSignIn(idToken: String): ResponseSignInDto {
        return apiService.postSignIn(idToken)
    }

    override suspend fun postNationality(requestNationalityDto: RequestNationalityDto): ResponseNationalityDto {
        return apiService.postNationality(requestNationalityDto)
    }
}