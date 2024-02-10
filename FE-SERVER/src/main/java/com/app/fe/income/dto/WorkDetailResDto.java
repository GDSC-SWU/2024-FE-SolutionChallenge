package com.app.fe.income.dto;

import com.app.fe.income.entity.WorkDetail;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WorkDetailResDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Detail {

        private Long tblWorkDetailId;
        private BigDecimal koreaSalary;
        private Integer workHour;
        private LocalDate workDay;

        @Builder
        public Detail(Long tblWorkDetailId, BigDecimal koreaSalary, Integer workHour, LocalDate workDay) {
            this.tblWorkDetailId = tblWorkDetailId;
            this.koreaSalary = koreaSalary;
            this.workHour = workHour;
            this.workDay = workDay;
        }

        public static Detail of(WorkDetail tblWorkDetail) {
            return Detail.builder()
                    .tblWorkDetailId(tblWorkDetail.getTblWorkDetailId())
                    .koreaSalary(tblWorkDetail.getKoreaSalary())
                    .workHour(tblWorkDetail.getWorkHour())
                    .workDay(tblWorkDetail.getWorkDay())
                    .build();
        }
    }
}
