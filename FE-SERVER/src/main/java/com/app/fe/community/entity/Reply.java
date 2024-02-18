package com.app.fe.community.entity;

import com.app.fe.common.entity.MemberBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "TBL_REPLY")
public class Reply extends MemberBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TBL_REPLY_ID")
    private Long tblReplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TBL_COMMUNITY_ID")
    private Community tblCommunity;

    @Column(name = "REPLY_CONTENT")
    private String replyContent;

    @Builder
    public Reply(Community tblCommunity, String replyContent) {
        this.tblCommunity = tblCommunity;
        this.replyContent = replyContent;
    }

    public void update(Reply updateData) {
        this.replyContent = updateData.getReplyContent();
    }
}
