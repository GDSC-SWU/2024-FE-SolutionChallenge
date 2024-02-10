package com.app.fe.income.repository;

import com.app.fe.income.entity.Income;
import com.app.fe.income.entity.WorkDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkDetailRepository extends JpaRepository<WorkDetail, Long> {

    List<WorkDetail> findAllByTblIncome(@Param("tblIncome") Income tblIncome);

    default WorkDetail getTblWorkDetail(Long tblWorkDetailId) {
        return findById(tblWorkDetailId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 급여 상세 정보 입니다."));
    }
}
