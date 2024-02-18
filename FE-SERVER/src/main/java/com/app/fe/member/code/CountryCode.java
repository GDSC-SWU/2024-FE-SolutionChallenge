package com.app.fe.member.code;

import com.app.fe.common.model.EnumCodeType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CountryCode implements EnumCodeType {

    KOREA("KOREA"),
    CHINA("CHINA"),
    VIETNAM("VIETNAM"),
    UNITED_STATES("UNITED_STATES");

    private final String value;
}
