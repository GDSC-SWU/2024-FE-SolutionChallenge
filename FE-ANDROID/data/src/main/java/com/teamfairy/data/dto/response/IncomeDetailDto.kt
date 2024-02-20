package com.teamfairy.data.dto.response

import com.teamfairy.domain.entity.WorkCheckEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@kotlinx.serialization.Serializable
data class IncomeDetailDto(
    @SerialName("timestamp")
    val timestamp: String,
    @SerialName("status")
    val status: Int,
    @SerialName("errorCode")
    val errorCode: String?,
    @SerialName("error")
    val error: String?,
    @SerialName("request")
    val request: String,
    @SerialName("response")
    val response: ResponseIncomeDetailData,
    @SerialName("path")
    val path: String?
)

@kotlinx.serialization.Serializable
data class ResponseIncomeDetailData(
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
    val workDetails: List<IncomeWorkDetail>
)

@Serializable
data class IncomeWorkDetail(
    @SerialName("tblWorkDetailId")
    val tblWorkDetailId: Int,
    @SerialName("koreaSalary")
    val koreaSalary: Double,
    @SerialName("workHour")
    val workHour: Int?,
    @SerialName("workDay")
    val workDay: String?
) {
    fun toIncomeDetailEntity(payday: String) = WorkCheckEntity(
        tblWorkDetailId,
        koreaSalary.toInt(),
        workHour,
        payday
    )
}

private fun set(data: String?): String {
    val today = LocalDate.now()

    // 년도와 월 가져오기
    val year = today.year
    val month = today.monthValue

    val formattedDate = String.format("%04d-%02d", year, month)
    return formattedDate + "${if (data?.length == 1) "0$data" else data}"
}