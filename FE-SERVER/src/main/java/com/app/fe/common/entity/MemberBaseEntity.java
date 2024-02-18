package com.app.fe.common.entity;

import com.app.fe.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class MemberBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID")
    private Member creator;

    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATER_ID")
    private Member updater;

    private LocalDateTime updatedDate;

    public void creator(Member creator) {
        this.creator = creator;
    }

    public void updater(Member updater) {
        this.updater = updater;
        this.updatedDate = LocalDateTime.now();
    }
}