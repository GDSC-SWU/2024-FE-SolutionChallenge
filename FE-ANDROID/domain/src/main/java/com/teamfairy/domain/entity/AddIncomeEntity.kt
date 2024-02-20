package com.teamfairy.domain.entity

data class AddIncomeEntity(
    val officeName: String,
    val incomeDay: String,
    val salaryType: String,
    val tblWorks: List<WorkEntity>,
    val fromWorkDay: String,
    val toWorkDay: String,
    val taxYn: Boolean
)

data class WorkEntity(
    val koreaSalary: Double, val workHour: Int? = null, val workDay: String? = null
)