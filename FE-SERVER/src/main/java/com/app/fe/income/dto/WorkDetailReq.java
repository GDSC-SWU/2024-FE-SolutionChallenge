package com.app.fe.income.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WorkDetailReq {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Create {

        private BigDecimal koreaSalary;
        private Integer workHour;
        private LocalDate workDay;

        @Builder
        public Create(BigDecimal koreaSalary, Integer workHour, LocalDate workDay) {
            this.koreaSalary = koreaSalary;
            this.workHour = workHour;
            this.workDay = workDay;
        }
    }
}
