package com.teamfairy.data.dto.response

import com.teamfairy.domain.entity.IncomeCardEntity
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
    fun toIncomeCardEntity() = workDetails[0].workDay?.let {
        IncomeCardEntity(
            tblIncomeId, officeName, incomeDay, it, currntSalary.toString(), null
        )
    }
}

@Serializable
data class WorkDetail(
    @SerialName("tblWorkDetailId") val tblWorkDetailId: Int,
    @SerialName("koreaSalary") val koreaSalary: Double,
    @SerialName("workHour") val workHour: Int?,
    @SerialName("workDay") val workDay: String?
) {
    fun toIncomeDetailCardEntity() = workDay?.let {
        IncomeCardEntity(
            tblWorkDetailId, null, null, it, koreaSalary.toString(), workHour.toString()
        )
    }
}