package com.app.fe.income.entity;

import com.app.fe.income.code.SalaryType;
import com.app.fe.common.entity.BaseEntity;
import com.app.fe.common.util.BooleanToYnConverter;
import com.app.fe.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "TBL_INCOME")
public class Income extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TBL_INCOME_ID")
    private Long tblIncomeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member tblMember;

    @Column(name = "OFFICE_NAME")
    private String officeName;

    @Column(name = "INCOME_DAY")
    private String incomeDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALARY_TYPE")
    private SalaryType salaryType;

    @Column(name = "FROM_WORK_DAY")
    private LocalDate fromWorkDay;

    @Column(name = "TO_WORK_DAY")
    private LocalDate toWorkDay;

    @Convert(converter = BooleanToYnConverter.class)
    @Column(name = "TAX_YN")
    private Boolean taxYn;

    @Convert(converter = BooleanToYnConverter.class)
    @Column(name = "USE_YN")
    private Boolean useYn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tblIncome")
    private final List<WorkDetail> workDetails = new ArrayList<>();

    @Builder
    public Income(Member tblMember, String officeName, String incomeDay,
                     SalaryType salaryType, Boolean taxYn, LocalDate fromWorkDay, LocalDate toWorkDay) {
        this.tblMember = tblMember;
        this.officeName = officeName;
        this.incomeDay = incomeDay;
        this.salaryType = salaryType;
        this.taxYn = taxYn;
        this.fromWorkDay = fromWorkDay;
        this.toWorkDay = toWorkDay;
    }

    public void use(Boolean useYn) {
        this.useYn = useYn;
    }

    public void addWorkDetails(List<WorkDetail> workDetails) {
        this.workDetails.addAll(workDetails);
    }
}