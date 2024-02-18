package com.app.fe.income.repository;

import com.app.fe.income.entity.Income;
import com.app.fe.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query(value = "SELECT t FROM Income t WHERE t.tblMember = :tblMember ORDER BY t.useYn ASC")
    List<Income> getAllByTblMember(@Param("tblMember") Member tblMember);

    default Income getTblIncome(Long tblIncomeId) {
        return findById(tblIncomeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 급여 정보 입니다."));
    }
}
