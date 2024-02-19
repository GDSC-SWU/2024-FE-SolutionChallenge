package com.teamfairy.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseRefreshTokenDto(
    @SerialName("accessToken") val accessToken: String,
)