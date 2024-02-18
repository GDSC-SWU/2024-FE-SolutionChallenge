package com.app.fe.community.repository;

import com.app.fe.community.entity.CommunityFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityFileRepository extends JpaRepository<CommunityFile, Long> {

    default CommunityFile getTblCommunityFile(Long tblCommunityFileId) {
        return findById(tblCommunityFileId).orElseThrow(() -> new IllegalArgumentException("파일이 존재하지 않습니다."));
    }
}
