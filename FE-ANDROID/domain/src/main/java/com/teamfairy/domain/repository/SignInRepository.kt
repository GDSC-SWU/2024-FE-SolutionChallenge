package com.teamfairy.domain.repository

import com.teamfairy.domain.entity.AuthEntity

interface SignInRepository {
    suspend fun postLogin(idToken:String) : Result<AuthEntity?>
}