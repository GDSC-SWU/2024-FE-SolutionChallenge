package com.app.fe.income.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TaxValue {

    TAX(3.3);

    private final double value;
}
