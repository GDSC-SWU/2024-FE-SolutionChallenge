package com.app.fe.common.entity;

import com.app.fe.common.util.BooleanToYnConverter;
import com.app.fe.community.code.FileType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class FileBaseEntity extends MemberBaseEntity {

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "FILE_UUID")
    private String fileUuid;

    @Column(name = "FILE_ORG_NAME")
    private String fileOrgName;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "FILE_TYPE")
    private FileType fileType;

    @Convert(converter = BooleanToYnConverter.class)
    @Column(name = "DEL_YN")
    private Boolean delYn;

    public void deleteFile() {
        this.delYn = false;
    }

    public FileBaseEntity(String filePath, String fileUuid, String fileOrgName, Long fileSize, FileType fileType) {
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileOrgName = fileOrgName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.delYn = false;
    }
}
