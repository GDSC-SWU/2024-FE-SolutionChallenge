package com.app.fe.common.dto;

import com.app.fe.member.entity.Member;
import com.app.fe.common.entity.BaseEntity;
import com.app.fe.common.entity.MemberBaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseRes {

    private Long creatorId;
    private String creatorName;
    private String creatorProfile;
    private LocalDateTime createdDate;

    private Long updaterId;
    private String updaterName;
    private String updaterProfile;
    private LocalDateTime updatedDate;

    public void initBaseRes(BaseEntity baseEntity) {
        this.createdDate = baseEntity.getCreatedDate();
        this.updatedDate = baseEntity.getUpdatedDate();
    }

    public void initMemberBaseRes(MemberBaseEntity memberBaseEntity) {
        Member creator = memberBaseEntity.getCreator();
        Member updater = memberBaseEntity.getUpdater();

        if (Objects.nonNull(creator)) {
            this.creatorId = creator.getTblMemberId();
            this.creatorProfile = creator.getPicture();
            this.creatorName = creator.getName();
            this.createdDate = memberBaseEntity.getCreatedDate();
        }

        if (Objects.nonNull(updater)) {
            this.updaterId = updater.getTblMemberId();
            this.updaterProfile = updater.getPicture();
            this.updaterName = updater.getName();
            this.updatedDate = memberBaseEntity.getCreatedDate();
        }
    }
}
