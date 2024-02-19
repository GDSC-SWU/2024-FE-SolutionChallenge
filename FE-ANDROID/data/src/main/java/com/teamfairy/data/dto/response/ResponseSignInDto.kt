package com.teamfairy.data.dto.response

import com.teamfairy.domain.entity.AuthEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignInDto(
    @SerialName("accessToken") val accessToken: String,
    @SerialName("refreshToken") val refreshToken: String
) {
    fun toAuthEntity() = AuthEntity(accessToken, refreshToken)
}