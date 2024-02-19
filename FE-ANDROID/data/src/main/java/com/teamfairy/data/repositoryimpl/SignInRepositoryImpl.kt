package com.teamfairy.data.repositoryimpl

import com.teamfairy.data.datasource.SignInDataSource
import com.teamfairy.domain.entity.AuthEntity
import com.teamfairy.domain.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInDataSource: SignInDataSource
) : SignInRepository {
    override suspend fun postLogin(idToken: String): Result<AuthEntity?> = runCatching {
        signInDataSource.postSignIn(idToken).data?.toAuthEntity()
    }
}