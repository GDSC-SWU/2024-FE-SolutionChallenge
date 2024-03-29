package com.teamfairy.data.datasource

import com.teamfairy.data.dto.request.RequestCommunityList
import com.teamfairy.data.dto.request.RequestComunityPostingDto
import com.teamfairy.data.dto.request.RequestPostCommentDto
import com.teamfairy.data.dto.response.ResponseCommunityListDto

interface CommunityDataSource {
    suspend fun postCommunityList(request: RequestCommunityList): ResponseCommunityListDto
    suspend fun postCommunityPosting(request: RequestComunityPostingDto): Unit
    suspend fun postCommunityDetail(tblCommunityId: Int): List<Unit>
    suspend fun deleteCommunityFeed(tblCommunityId: Int): String
    suspend fun postComment(request: RequestPostCommentDto): Unit
    suspend fun deleteComment(tblReplyId: Int): String
}