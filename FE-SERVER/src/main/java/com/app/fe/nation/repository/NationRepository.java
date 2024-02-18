package com.app.fe.nation.repository;

import com.app.fe.nation.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationRepository extends JpaRepository<Nation, Long> {

    default Nation getTblNation(Long tblNationId) {
        return findById(tblNationId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 국가정보 입니다."));
    }
}
