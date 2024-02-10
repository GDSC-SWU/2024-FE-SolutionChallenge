package com.app.fe.income.entity;

import com.app.fe.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "TBL_WORK_DETAIL")
public class WorkDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TBL_WORK_DETAIL_ID")
    private Long tblWorkDetailId;

    @Column(name = "KOREA_SALARY")
    private BigDecimal koreaSalary;

    @Column(name = "WORK_HOUR")
    private Integer workHour;

    @Column(name = "WORK_DAY")
    private LocalDate workDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TBL_INCOME_ID")
    private Income tblIncome;

    @Builder
    public WorkDetail(BigDecimal koreaSalary, Integer workHour, LocalDate workDay, Income tblIncome) {
        this.koreaSalary = koreaSalary;
        this.workHour = workHour;
        this.workDay = workDay;
        this.tblIncome = tblIncome;
    }
}
