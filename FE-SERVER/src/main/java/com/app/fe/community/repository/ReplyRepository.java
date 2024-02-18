package com.app.fe.community.repository;

import com.app.fe.community.entity.Community;
import com.app.fe.community.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 내 게시물 전부 조회
    Page<Reply> findAllByTblCommunity(@Param("tblCommunity") Community tblCommunity, Pageable pageable);

    default Reply getTblReply(Long tblReplyId) {
        return findById(tblReplyId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));
    }
}
