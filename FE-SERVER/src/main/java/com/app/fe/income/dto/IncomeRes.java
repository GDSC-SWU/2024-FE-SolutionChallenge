package com.app.fe.income.dto;

import com.app.fe.income.code.TaxValue;
import com.app.fe.income.code.OrderType;
import com.app.fe.income.code.SalaryType;
import com.app.fe.income.entity.Income;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IncomeRes {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Detail {
        private Long tblIncomeId;
        private String officeName;
        private String incomeDay;
        private SalaryType salaryType;
        private Boolean taxYn;

        @Setter
        private BigDecimal currntSalary;

        @Setter
        private List<WorkDetailRes.Detail> workDetails;

        @Builder
        public Detail(Long tblIncomeId, String officeName, String incomeDay, SalaryType salaryType,
                      Boolean taxYn, List<WorkDetailRes.Detail> workDetails) {
            this.tblIncomeId = tblIncomeId;
            this.officeName = officeName;
            this.incomeDay = incomeDay;
            this.salaryType = salaryType;
            this.taxYn = taxYn;
            this.workDetails = workDetails;
        }

        public static Detail of(Income tblIncome) {
            Detail detail = Detail.builder()
                    .tblIncomeId(tblIncome.getTblIncomeId())
                    .officeName(tblIncome.getOfficeName())
                    .incomeDay(tblIncome.getIncomeDay())
                    .salaryType(tblIncome.getSalaryType())
                    .taxYn(tblIncome.getTaxYn())
                    .workDetails(CollectionUtils.isEmpty(tblIncome.getWorkDetails()) ?
                            null : tblIncome.getWorkDetails().stream()
                            .map(WorkDetailRes.Detail::of).collect(Collectors.toList()))
                    .build();

            // 급여 계산
            calSalary(detail);
            return detail;
        }

        public static Detail of(Income tblIncome, OrderType orderType) {
            Detail detail = Detail.builder()
                    .tblIncomeId(tblIncome.getTblIncomeId())
                    .officeName(tblIncome.getOfficeName())
                    .incomeDay(tblIncome.getIncomeDay())
                    .salaryType(tblIncome.getSalaryType())
                    .taxYn(tblIncome.getTaxYn())
                    .workDetails(tblIncome.getWorkDetails().stream()
                            .filter(Objects::nonNull)
                            .map(WorkDetailRes.Detail::of).collect(Collectors.toList()))
                    .build();

            List<WorkDetailRes.Detail> workDetailsOrder = detail.getWorkDetails();

            /**
             * 명령에 따른 정렬
             */
            if (!CollectionUtils.isEmpty(workDetailsOrder)) {
                if (orderType.equals(OrderType.ASC)) {
                    detail.setWorkDetails(workDetailsOrder.stream().sorted(Comparator.comparing(WorkDetailRes.Detail::getWorkDay).reversed())
                            .collect(Collectors.toList()));
                } else {
                    detail.setWorkDetails(workDetailsOrder.stream().sorted(Comparator.comparing(WorkDetailRes.Detail::getWorkDay))
                            .collect(Collectors.toList()));
                }
            }

            // 급여 계산
            calSalary(detail);
            return detail;
        }

        // 숫자 X (1 - 퍼센트 ÷ 100)
        public static void calSalary(Detail detail) {
            if (CollectionUtils.isEmpty(detail.getWorkDetails())) {
                return;
            }

            SalaryType salaryType = detail.getSalaryType();
            WorkDetailRes.Detail workDetail = detail.getWorkDetails().get(0);
            BigDecimal currentSalary = workDetail.getKoreaSalary();
            BigDecimal tax = BigDecimal.ONE.subtract(BigDecimal.valueOf(TaxValue.TAX.getValue()).divide(BigDecimal.valueOf(100), 3, RoundingMode.DOWN));

            switch (salaryType) {
                // 월급의 경우
                case MONTHLY_PAY, WEEKLY_PAY -> {
                    if (detail.getTaxYn()) {
                        currentSalary = detail.getWorkDetails().get(0).getKoreaSalary()
                                .multiply(tax);
                    }
                }
                // 시급의 경우
                case HOURLY_WAGE -> {
                    List<WorkDetailRes.Detail> tempWorkDetails = detail.getWorkDetails();

                    // 금액 합산
                    currentSalary = tempWorkDetails.stream()
                            .map(wd -> wd.getKoreaSalary().multiply(BigDecimal.valueOf(wd.getWorkHour()))).reduce(BigDecimal.ZERO, BigDecimal::add);

                    if (detail.getTaxYn()) {
                        currentSalary = currentSalary.multiply(tax);
                    }
                }
            }

            detail.setCurrntSalary(currentSalary);
        }
    }
}
