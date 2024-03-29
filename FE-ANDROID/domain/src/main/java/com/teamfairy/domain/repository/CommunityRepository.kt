package com.teamfairy.domain.repository

import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.domain.entity.PostCommentEntity
import com.teamfairy.domain.entity.PostingFeedEntity

interface CommunityRepository {
    suspend fun postCommunityList(type: String): Result<List<FeedEntity>>
    suspend fun postCommunityPosting(request: PostingFeedEntity): Result<Unit>
    suspend fun postCommunityDetail(tblCommunityId: Int): Result<List<Unit>>
    suspend fun deleteCommunityFeed(tblCommunityId: Int): Result<String>
    suspend fun postComment(request: PostCommentEntity): Result<Unit>
    suspend fun deleteComment(tblReplyId: Int): Result<String>
}