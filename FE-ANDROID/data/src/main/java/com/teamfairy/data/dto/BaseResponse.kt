package com.teamfairy.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("timestamp")
    val timestamp: String?,
    @SerialName("status")
    val status: Int?,
    @SerialName("errorCode")
    val errorCode: Int?,
    @SerialName("error")
    val error: String?,
    @SerialName("request")
    val request: T? = null,
    @SerialName("response")
    val response: T? = null,
    @SerialName("path")
    val path: String? = null,
)
