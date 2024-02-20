package com.teamfairy.data.dto.request

import com.teamfairy.domain.entity.PostingFeedEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestComunityPostingDto(
    @SerialName("communityType") val communityType: String,
    @SerialName("communityTitle") val communityTitle: String,
    @SerialName("communityContent") val communityContent: String,
    @SerialName("fileType") val fileType: String
) {
    fun toPostingFeedEntity() =
        PostingFeedEntity(communityType, communityTitle, communityContent, fileType)
}