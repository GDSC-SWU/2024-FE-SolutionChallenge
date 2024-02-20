package com.teamfairy.data.repositoryimpl

import com.teamfairy.data.datasource.IncomeDataSource
import com.teamfairy.data.dto.request.RequestIncomeAddDto
import com.teamfairy.data.dto.request.Work
import com.teamfairy.domain.entity.AddIncomeEntity
import com.teamfairy.domain.entity.IncomeCardEntity
import com.teamfairy.domain.entity.WorkCheckEntity
import com.teamfairy.domain.repository.IncomeRepository
import javax.inject.Inject

class IncomeRepositoryImpl @Inject constructor(
    private val dataSource: IncomeDataSource
) : IncomeRepository {
    override suspend fun postIncomeList(): Result<List<IncomeCardEntity?>> = runCatching {
        dataSource.postIncomeList().map { it.toIncomeCardEntity() }
    }

    override suspend fun postAddIncome(request: AddIncomeEntity): Result<Int> = runCatching {
        dataSource.postAddIncome(
            RequestIncomeAddDto(
                request.officeName,
                request.incomeDay,
                request.salaryType,
                request.tblWorks.map { Work(it.koreaSalary, it.workHour, it.workDay) },
                request.fromWorkDay,
                request.toWorkDay,
                request.taxYn
            )
        ).toIncomeCardEntity()?.incomeId ?: 0
    }

    override suspend fun postIncomeDetail(
        incomeId: Int, oderType: String
    ): Result<List<WorkCheckEntity?>> = kotlin.runCatching {
        dataSource.postIncomeDetail(incomeId, "DESC").workDetails.map {
            it.toIncomeDetailCardEntity()
        }
    }


    override suspend fun deleteIncome(incomeId: Int): String = kotlin.runCatching {
        dataSource.deleteIncome(incomeId) ?: ""
    }.toString()
}