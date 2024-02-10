package com.app.fe.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class FileBaseEntity extends BaseEntity {

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "FILE_UUID")
    private String fileUuid;

    @Column(name = "FILE_SIZE")
    private String fileSize;

    @Column(name = "FILE_TYPE")
    private String fileType;
}
