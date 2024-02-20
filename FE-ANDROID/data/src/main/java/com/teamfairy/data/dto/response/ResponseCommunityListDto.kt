package com.teamfairy.data.dto.response

import com.teamfairy.domain.entity.FeedEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCommunityListDto(
    @SerialName("timestamp")
    val timestamp: String,
    @SerialName("status")
    val status: Int,
    @SerialName("errorCode")
    val errorCode: String?,
    @SerialName("error")
    val error: String?,
    @SerialName("request")
    val request: Request,
    @SerialName("response")
    val response: Response,
    @SerialName("path")
    val path: String?
)

@Serializable
data class Request(
    @SerialName("maxMonthPeriodGap")
    val maxMonthPeriodGap: Int,
    @SerialName("currentPage")
    val currentPage: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("orderProperty")
    val orderProperty: String,
    @SerialName("direction")
    val direction: String,
    @SerialName("searchDateType")
    val searchDateType: String,
    @SerialName("searchFromDate")
    val searchFromDate: String,
    @SerialName("searchToDate")
    val searchToDate: String,
    @SerialName("createdEmpId")
    val createdEmpId: Int?, // Change the type to appropriate
    @SerialName("updatedEmpId")
    val updatedEmpId: Int?, // Change the type to appropriate
    @SerialName("searchFromDateToString")
    val searchFromDateToString: String,
    @SerialName("searchToDateToString")
    val searchToDateToString: String
)

@Serializable
data class Response(
    @SerialName("items")
    val items: List<Item>,
    @SerialName("pageRes")
    val pageRes: PageRes
)

@Serializable
data class Item(
    @SerialName("creatorId")
    val creatorId: Int,
    @SerialName("creatorName")
    val creatorName: String,
    @SerialName("creatorProfile")
    val creatorProfile: String?,
    @SerialName("createdDate")
    val createdDate: String,
    @SerialName("updaterId")
    val updaterId: Int?, // Change the type to appropriate
    @SerialName("updaterName")
    val updaterName: String?, // Change the type to appropriate
    @SerialName("updaterProfile")
    val updaterProfile: String?, // Change the type to appropriate
    @SerialName("updatedDate")
    val updatedDate: String?, // Change the type to appropriate
    @SerialName("tblCommunityId")
    val tblCommunityId: Int,
    @SerialName("communityTypeCode")
    val communityTypeCode: String,
    @SerialName("communityTypeValue")
    val communityTypeValue: String,
    @SerialName("communityTitle")
    val communityTitle: String?, // Change the type to appropriate
    @SerialName("communityContent")
    val communityContent: String,
    @SerialName("thumbNailFile")
    val thumbNailFile: String? // Change the type to appropriate
) {
    fun toFeedEntity() =
        FeedEntity(
            tblCommunityId,
            communityTitle ?: "",
            updaterName ?: "",
            createdDate,
            communityContent
        )
}

@Serializable
data class PageRes(
    @SerialName("totalElements")
    val totalElements: Int,
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("pageNumber")
    val pageNumber: Int,
    @SerialName("numberOfElements")
    val numberOfElements: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("first")
    val first: Boolean,
    @SerialName("empty")
    val empty: Boolean,
    @SerialName("last")
    val last: Boolean,
    @SerialName("pageStartNumber")
    val pageStartNumber: Int,
    @SerialName("pageEndNumber")
    val pageEndNumber: Int
)