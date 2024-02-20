package com.teamfairy.data.repositoryimpl

import com.teamfairy.data.datasource.SignInDataSource
import com.teamfairy.data.dto.request.RequestNationalityDto
import com.teamfairy.domain.entity.AuthEntity
import com.teamfairy.domain.entity.UserInfoEntity
import com.teamfairy.domain.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInDataSource: SignInDataSource
) : SignInRepository {
    override suspend fun postLogin(idToken: String): Result<AuthEntity?> = runCatching {
        signInDataSource.postSignIn(idToken).toAuthEntity()
    }

    override suspend fun postNationality(
        memberId: String, nationality: String
    ): Result<UserInfoEntity> = runCatching {
        signInDataSource.postNationality(RequestNationalityDto(memberId, nationality))
            .toUserInfoEntity()
    }
}