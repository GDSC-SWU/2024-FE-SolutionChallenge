package com.teamfairy.data.api

import com.teamfairy.data.dto.request.RequestCommunityList
import com.teamfairy.data.dto.request.RequestComunityPostingDto
import com.teamfairy.data.dto.request.RequestPostCommentDto
import com.teamfairy.data.dto.response.ResponseCommunityListDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface CommunityApiService {
    @Multipart
    @POST("api/v1/community/create")
    suspend fun postCommunityPosting(
        @Part("tblCommunityReq") request : RequestComunityPostingDto,
    ): Unit

    @POST("api/v1/community/search")
    suspend fun postCommunityList(
        @Body request: RequestCommunityList
    ): ResponseCommunityListDto

    @GET("api/v1/community/detail/{tblCommunityId}")
    suspend fun postCommunityDetail(
        @Path(value = "tblCommunityId") tblCommunityId: Int
    ): List<Unit>

    @DELETE("api/v1/community/delete/{tblCommunityId}")
    suspend fun deleteCommunityFeed(
        @Path(value = "tblCommunityId") tblCommunityId: Int
    ): String

    @POST("api/v1/community/reply/create")
    suspend fun postComment(
        @Body request: RequestPostCommentDto
    ): Unit

    @DELETE("api/v1/community/reply/delete/{tblReplyId}")
    suspend fun deleteComment(
        @Path(value = "tblReplyId") tblReplyId: Int
    ): String
}