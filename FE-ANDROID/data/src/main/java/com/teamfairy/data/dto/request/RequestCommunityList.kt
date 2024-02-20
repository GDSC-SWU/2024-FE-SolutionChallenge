package com.teamfairy.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCommunityList (
    @SerialName("communityType") val communityType: String,
)