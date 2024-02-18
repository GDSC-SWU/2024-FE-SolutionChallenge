package com.app.fe.community.repository;

import com.app.fe.community.entity.Community;
import com.app.fe.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    // 내 게시물 전부 조회
    Page<Community> findAllByCreator(@Param("creator") Member creator, Pageable pageable);

    default Community getTblCommunity(Long tblCommunityId) {
        return findById(tblCommunityId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));
    }
}
