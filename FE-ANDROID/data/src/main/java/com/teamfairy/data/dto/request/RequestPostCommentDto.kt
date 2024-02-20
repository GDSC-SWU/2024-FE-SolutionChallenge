package com.teamfairy.data.dto.request

import com.teamfairy.domain.entity.PostCommentEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostCommentDto(
    @SerialName("tblCommunityId") val tblCommunityId: Int,
    @SerialName("replyContent") val replyContent: String,
) {
    fun toPostCommentEntity() = PostCommentEntity(tblCommunityId, replyContent)
}