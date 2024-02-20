package com.teamfairy.data.repositoryimpl

import com.teamfairy.data.datasource.CommunityDataSource
import com.teamfairy.data.dto.request.RequestCommunityList
import com.teamfairy.data.dto.request.RequestComunityPostingDto
import com.teamfairy.data.dto.request.RequestPostCommentDto
import com.teamfairy.domain.entity.FeedEntity
import com.teamfairy.domain.entity.PostCommentEntity
import com.teamfairy.domain.entity.PostingFeedEntity
import com.teamfairy.domain.repository.CommunityRepository
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val dataSource: CommunityDataSource
) : CommunityRepository {
    override suspend fun postCommunityList(type: String): Result<List<FeedEntity>> =
        runCatching {
            dataSource.postCommunityList(RequestCommunityList(type)).response.items.map {
                it.toFeedEntity()
            }
        }

    override suspend fun postCommunityPosting(request: PostingFeedEntity): Result<Unit> =
        runCatching {
            dataSource.postCommunityPosting(
                RequestComunityPostingDto(
                    request.communityType,
                    request.communityTitle,
                    request.communityContent,
                    request.fileType
                )
            )
        }

    override suspend fun postCommunityDetail(tblCommunityId: Int): Result<List<Unit>> =
        runCatching {
            dataSource.postCommunityDetail(tblCommunityId)
        }

    override suspend fun deleteCommunityFeed(tblCommunityId: Int): Result<String> = runCatching {
        dataSource.deleteCommunityFeed(tblCommunityId)
    }

    override suspend fun postComment(request: PostCommentEntity): Result<Unit> = runCatching {
        dataSource.postComment(RequestPostCommentDto(request.tblCommunityId, request.replyContent))
    }

    override suspend fun deleteComment(tblReplyId: Int): Result<String> = runCatching {
        dataSource.deleteComment(tblReplyId)
    }
}