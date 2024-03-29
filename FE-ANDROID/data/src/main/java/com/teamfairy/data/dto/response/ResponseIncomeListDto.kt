package com.teamfairy.data.dto.response

import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.domain.entity.WorkCheckEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseIncomeListDto(
    @SerialName("tblIncomeId") val tblIncomeId: Int,
    @SerialName("officeName") val officeName: String,
    @SerialName("incomeDay") val incomeDay: String,
    @SerialName("salaryType") val salaryType: String,
    @SerialName("taxYn") val taxYn: Boolean,
    @SerialName("currntSalary") val currntSalary: Double,
    @SerialName("workDetails") val workDetails: List<WorkDetail>
) {
    fun toIncomeCardEntity() = IncomeCardEntity(
        tblIncomeId, officeName, incomeDay, workDetails[0].workDay ?: "-1", currntSalary.toString(), null
    )
}

@Serializable
data class WorkDetail(
    @SerialName("tblWorkDetailId") val tblWorkDetailId: Int,
    @SerialName("koreaSalary") val koreaSalary: Double,
    @SerialName("workHour") val workHour: Int?,
    @SerialName("workDay") val workDay: String?
) {
    fun toIncomeDetailCardEntity() = workDay?.let {
        WorkCheckEntity(
            tblWorkDetailId, koreaSalary.toInt(), workHour, workDay
        )
    }
}