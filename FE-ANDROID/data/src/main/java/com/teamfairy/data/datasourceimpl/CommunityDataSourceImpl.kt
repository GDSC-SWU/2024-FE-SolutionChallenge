package com.teamfairy.data.datasourceimpl

import com.teamfairy.data.api.CommunityApiService
import com.teamfairy.data.datasource.CommunityDataSource
import com.teamfairy.data.dto.request.RequestCommunityList
import com.teamfairy.data.dto.request.RequestComunityPostingDto
import com.teamfairy.data.dto.request.RequestPostCommentDto
import com.teamfairy.data.dto.response.ResponseCommunityListDto
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class CommunityDataSourceImpl @Inject constructor(
    private val apiService: CommunityApiService
) : CommunityDataSource {
    override suspend fun postCommunityList(request: RequestCommunityList): ResponseCommunityListDto {
        return apiService.postCommunityList(request)
    }

    override suspend fun postCommunityPosting(request: RequestComunityPostingDto) {
        return apiService.postCommunityPosting(
            request
        )
    }

    override suspend fun postCommunityDetail(tblCommunityId: Int): List<Unit> {
        return apiService.postCommunityDetail(tblCommunityId)
    }

    override suspend fun deleteCommunityFeed(tblCommunityId: Int): String {
        return apiService.deleteCommunityFeed(tblCommunityId)
    }

    override suspend fun postComment(request: RequestPostCommentDto) {
        return apiService.postComment(request)
    }

    override suspend fun deleteComment(tblReplyId: Int): String {
        return apiService.deleteComment(tblReplyId)
    }
}