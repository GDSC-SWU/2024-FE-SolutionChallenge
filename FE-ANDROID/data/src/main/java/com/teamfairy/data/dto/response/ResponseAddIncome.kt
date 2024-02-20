package com.teamfairy.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAddIncomeDto(
    @SerialName("timestamp")
    val timestamp: String,
    @SerialName("status")
    val status: Int,
    @SerialName("errorCode")
    val errorCode: String?,
    @SerialName("error")
    val error: String?,
    @SerialName("request")
    val request: RequestAddIncome,
    @SerialName("response")
    val response: ResponseAddIncome,
    @SerialName("path")
    val path: String?
)

@Serializable
data class RequestAddIncome(
    @SerialName("officeName")
    val officeName: String,
    @SerialName("incomeDay")
    val incomeDay: String,
    @SerialName("salaryType")
    val salaryType: String,
    @SerialName("tblWorks")
    val tblWorks: List<TblWork>,
    @SerialName("fromWorkDay")
    val fromWorkDay: String,
    @SerialName("toWorkDay")
    val toWorkDay: String,
    @SerialName("taxYn")
    val taxYn: Boolean
)

@Serializable
data class TblWork(
    @SerialName("koreaSalary")
    val koreaSalary: Double?,
    @SerialName("workHour")
    val workHour: Int?,
    @SerialName("workDay")
    val workDay: String?
)

@Serializable
data class ResponseAddIncome(
    @SerialName("tblIncomeId")
    val tblIncomeId: Int,
    @SerialName("officeName")
    val officeName: String,
    @SerialName("incomeDay")
    val incomeDay: String,
    @SerialName("salaryType")
    val salaryType: String,
    @SerialName("taxYn")
    val taxYn: Boolean,
    @SerialName("currntSalary")
    val currntSalary: Double,
    @SerialName("workDetails")
    val workDetails: List<WorkDetailAddIncome>
)

@Serializable
data class WorkDetailAddIncome(
    @SerialName("tblWorkDetailId")
    val tblWorkDetailId: Int,
    @SerialName("koreaSalary")
    val koreaSalary: Double?,
    @SerialName("workHour")
    val workHour: Int?,
    @SerialName("workDay")
    val workDay: String?
)