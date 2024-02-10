package com.app.fe.income.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SalaryType {

    MONTHLY_PAY("월급"),
    HOURLY_WAGE("시급"),
    WEEKLY_PAY("주급");

    private final String value;
}
