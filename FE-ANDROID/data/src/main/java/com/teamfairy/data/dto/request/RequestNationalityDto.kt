package com.teamfairy.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestNationalityDto(
    @SerialName("tblMemberId") val tblMemberId: String,
    @SerialName("tblNationId") val tblNationId: String,
)