package com.app.fe.income.code;

import com.app.fe.common.model.EnumCodeType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OrderType implements EnumCodeType {

    ASC("ASC"),
    DESC("DESC");

    private final String value;
}
