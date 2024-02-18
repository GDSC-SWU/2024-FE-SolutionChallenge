package com.app.fe.community.code;

import com.app.fe.common.model.EnumCodeType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommunityType implements EnumCodeType {

    DAILY("일상"),
    NOTICE("공지");
    
    private final String value;
}
