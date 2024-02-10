package com.app.fe.member.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CountryCode {

    KOREA("KOREA"),
    CHINA("CHINA"),
    UNITED_STATES("UNITED_STATES");

    private final String value;
}
