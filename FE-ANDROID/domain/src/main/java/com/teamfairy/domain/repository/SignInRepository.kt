package com.teamfairy.domain.repository

import com.teamfairy.domain.entity.AuthEntity
import com.teamfairy.domain.entity.UserInfoEntity

interface SignInRepository {
    suspend fun postLogin(idToken: String): Result<AuthEntity?>

    suspend fun postNationality(memberId: String, nationality: String): Result<UserInfoEntity>
}