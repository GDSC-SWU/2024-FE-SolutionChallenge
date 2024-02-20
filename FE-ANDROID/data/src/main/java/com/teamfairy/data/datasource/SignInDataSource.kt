package com.teamfairy.data.datasource

import com.teamfairy.data.dto.request.RequestNationalityDto
import com.teamfairy.data.dto.response.ResponseNationalityDto
import com.teamfairy.data.dto.response.ResponseSignInDto

interface SignInDataSource {
    suspend fun postSignIn(idToken: String): ResponseSignInDto

    suspend fun postNationality(requestNationalityDto: RequestNationalityDto): ResponseNationalityDto
}