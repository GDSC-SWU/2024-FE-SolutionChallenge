package com.app.fe.community.code;

import com.app.fe.common.model.EnumCodeType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FileType implements EnumCodeType {

    THUMB_NAIL("대표 이미지"),
    FILE("첨부 파일");
    
    private final String value;

    public String getFilePath() {
        return name().replace("_", "").trim().toLowerCase();
    }
}
