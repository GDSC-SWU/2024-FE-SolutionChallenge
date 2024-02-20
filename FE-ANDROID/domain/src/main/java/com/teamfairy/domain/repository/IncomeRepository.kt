package com.teamfairy.domain.repository

import com.teamfairy.domain.entity.AddIncomeEntity
import com.teamfairy.domain.entity.IncomeCardEntity

interface IncomeRepository {
    suspend fun postIncomeList(): Result<List<IncomeCardEntity?>>

    suspend fun postAddIncome(request: AddIncomeEntity): Result<Int>

    suspend fun postIncomeDetail(incomeId: Int, oderType: String): Result<List<IncomeCardEntity?>>

    suspend fun deleteIncome(incomeId: Int): String
}