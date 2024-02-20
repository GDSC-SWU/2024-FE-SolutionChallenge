package com.teamfairy.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestIncomeAddDto(
    @SerialName("officeName") val officeName: String,
    @SerialName("incomeDay") val incomeDay: String,
    @SerialName("salaryType") val salaryType: String,
    @SerialName("tblWorks") val tblWorks: List<Work>,
    @SerialName("fromWorkDay") val fromWorkDay: String,
    @SerialName("toWorkDay") val toWorkDay: String,
    @SerialName("taxYn") val taxYn: Boolean
)

@Serializable
data class Work(
    @SerialName("koreaSalary") val koreaSalary: Double,
    @SerialName("workHour") val workHour: Int? = null,
    @SerialName("workDay") val workDay: String? = null
)
