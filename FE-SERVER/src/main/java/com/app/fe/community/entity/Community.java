package com.app.fe.community.entity;

import com.app.fe.common.entity.MemberBaseEntity;
import com.app.fe.community.code.CommunityType;
import com.app.fe.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "TBL_COMMUNITY")
public class Community extends MemberBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TBL_COMMUNITY_ID")
    private Long tblCommunityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "COMMUNITY_TYPE")
    private CommunityType communityType;

    @Column(name = "COMMUNITY_TITLE")
    private String communityTitle;

    @Column(name = "COMMUNITY_CONTENT")
    private String communityContent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tblCommunity", cascade = CascadeType.PERSIST)
    private final List<Reply> tblReplies = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tblCommunity", cascade = CascadeType.PERSIST)
    private final List<CommunityFile> thumbNailFile = new ArrayList<>();

    @Builder
    public Community(CommunityType communityType, String communityTitle, String communityContent) {
        this.communityType = communityType;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
    }

    public void update(Community updateData) {
        this.communityType = updateData.getCommunityType();
        this.communityTitle = updateData.getCommunityTitle();
        this.communityContent = updateData.getCommunityContent();
    }

    public void creator(Member currentMember) {
        super.creator(currentMember);
    }

    public void addFile(CommunityFile tblCOMMUNITYFile) {
        this.thumbNailFile.add(tblCOMMUNITYFile);
    }

    public void addReply(Reply tblReply) {
        this.tblReplies.add(tblReply);
    }
}
