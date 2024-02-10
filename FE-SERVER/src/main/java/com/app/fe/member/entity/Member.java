package com.app.fe.member.entity;

import com.app.fe.common.entity.BaseEntity;
import com.app.fe.member.code.MemberRole;
import com.app.fe.nation.entity.Nation;
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
@Table(name = "TBL_MEMBER")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TBL_MEMBER_ID")
    private Long tblMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TBL_NATION_ID")
    private Nation tblNation;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PICTURE")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_ROLE")
    private MemberRole memberRole;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "PROVIDER_ID")
    private String providerId;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Builder
    public Member(String name, String email, String picture,
                  MemberRole memberRole, String provider, String providerId) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.memberRole = memberRole;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void updateProvider(String provider) {
        this.provider = provider;
    }

    public void update(Member updateData) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public void updateNation(Nation tblNation) {
        this.tblNation = tblNation;
    }

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void logout() {
        this.refreshToken = null;
    }
}
