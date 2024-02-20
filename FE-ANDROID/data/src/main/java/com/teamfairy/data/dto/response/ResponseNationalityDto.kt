package com.teamfairy.data.dto.response

import com.teamfairy.domain.entity.UserInfoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseNationalityDto(
    @SerialName("tblMemberId") val tblMemberId: Int,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("picture") val picture: String,
    @SerialName("memberRole") val memberRole: String,
    @SerialName("providerId") val providerId: String,
    @SerialName("tblNation") val tblNation: Nation
) {
    fun toUserInfoEntity() = UserInfoEntity(
        nickname = name,
        profileUrl = picture,
        nationality = tblNation.tblNationId.toString(),
    )
}

@Serializable
data class Nation(
    @SerialName("tblNationId") val tblNationId: Int,
    @SerialName("countryCode") val countryCode: String
)