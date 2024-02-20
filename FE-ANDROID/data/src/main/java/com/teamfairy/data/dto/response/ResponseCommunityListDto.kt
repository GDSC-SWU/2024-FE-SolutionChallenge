package com.teamfairy.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCommunityListDto(
    @SerialName("creatorId") val creatorId: Int,
    @SerialName("creatorName") val creatorName: String,
    @SerialName("creatorProfile") val creatorProfile: String,
    @SerialName("createdDate") val createdDate: String,
    @SerialName("updaterId") val updaterId: Int?,
    @SerialName("updaterName") val updaterName: String?,
    @SerialName("updaterProfile") val updaterProfile: String?,
    @SerialName("updatedDate") val updatedDate: String?,
    @SerialName("tblCommunityId") val tblCommunityId: Int,
    @SerialName("communityTypeCode") val communityTypeCode: String,
    @SerialName("communityTypeValue") val communityTypeValue: String,
    @SerialName("communityTitle") val communityTitle: String,
    @SerialName("communityContent") val communityContent: String,
    @SerialName("tblMember") val tblMember: Int?,
    @SerialName("thumbNailFile") val thumbNailFile: ThumbnailFile
)

@Serializable
data class ThumbnailFile(
    @SerialName("creatorId") val creatorId: Int,
    @SerialName("creatorName") val creatorName: String,
    @SerialName("creatorProfile") val creatorProfile: String,
    @SerialName("createdDate") val createdDate: String,
    @SerialName("updaterId") val updaterId: Int?,
    @SerialName("updaterName") val updaterName: String?,
    @SerialName("updaterProfile") val updaterProfile: String?,
    @SerialName("updatedDate") val updatedDate: String?,
    @SerialName("tblCommunityFileId") val tblCommunityFileId: Int,
    @SerialName("filePath") val filePath: String,
    @SerialName("fileUuid") val fileUuid: String,
    @SerialName("fileOrgName") val fileOrgName: String,
    @SerialName("fileSize") val fileSize: Int,
    @SerialName("fileType") val fileType: String
)