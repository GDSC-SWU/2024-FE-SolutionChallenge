package com.app.fe.community.entity;

import com.app.fe.common.entity.FileBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@SQLRestriction("DEL_YN = 'N'")
@Table(name = "TBL_COMMUNITY_FILE")
public class CommunityFile extends FileBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TBL_COMMUNITY_FILE_ID")
    private Long tblCommunityFileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TBL_COMMUNITY_ID")
    private Community tblCommunity;

    @Builder
    public CommunityFile(FileBaseEntity fileBaseEntity, Community tblCommunity) {
        super(fileBaseEntity.getFilePath(), fileBaseEntity.getFileUuid(), fileBaseEntity.getFileOrgName(),
                fileBaseEntity.getFileSize(), fileBaseEntity.getFileType());

    }
}
