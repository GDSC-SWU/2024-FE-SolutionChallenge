package com.teamfairy.feature.income

import android.os.Parcelable
import com.teamfairy.domain.entity.IncomeCardEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class IncomeCard(
    val incomeId: Int,
    val companyName: String,
    val payDay: String,
    val startDay: String,
    val currentSalary: String
) : Parcelable {
    constructor(incomeCardEntity: IncomeCardEntity) : this(
        incomeCardEntity.incomeId,
        incomeCardEntity.companyName,
        incomeCardEntity.payDay,
        incomeCardEntity.startDay,
        incomeCardEntity.currentSalary
    )

    fun toIncomeCardEntity() =
        IncomeCardEntity(incomeId, companyName, payDay, startDay, currentSalary)
}
