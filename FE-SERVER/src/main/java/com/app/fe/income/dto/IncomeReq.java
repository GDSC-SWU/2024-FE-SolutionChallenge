package com.app.fe.income.dto;

import com.app.fe.income.code.SalaryType;
import com.app.fe.income.entity.Income;
import com.app.fe.member.entity.Member;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class IncomeReq {

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Create {
        private String officeName;
        private String incomeDay;
        private SalaryType salaryType;
        private List<WorkDetailReq.Create> tblWorks;
        private LocalDate fromWorkDay;
        private LocalDate toWorkDay;
        private Boolean taxYn;

        public Income toEntity(Member tblMember) {
            return Income.builder()
                    .officeName(this.officeName)
                    .incomeDay(this.incomeDay)
                    .salaryType(this.salaryType)
                    .taxYn(this.taxYn)
                    .fromWorkDay(this.fromWorkDay)
                    .toWorkDay(this.toWorkDay)
                    .tblMember(tblMember)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Update {
        private Long tblIncomeId;
        private Boolean useYn;
    }
}
