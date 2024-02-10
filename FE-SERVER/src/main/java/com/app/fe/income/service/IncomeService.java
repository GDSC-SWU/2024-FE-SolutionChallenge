package com.app.fe.income.service;

import com.app.fe.income.code.OrderType;
import com.app.fe.income.dto.IncomeReqDto;
import com.app.fe.income.dto.IncomeResDto;
import com.app.fe.income.entity.Income;
import com.app.fe.income.entity.WorkDetail;
import com.app.fe.income.repository.IncomeRepository;
import com.app.fe.income.repository.WorkDetailRepository;
import com.app.fe.common.util.SecurityUtil;
import com.app.fe.member.entity.Member;
import com.app.fe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class IncomeService {

    private final SecurityUtil securityUtil;
    private final MemberRepository memberRepository;
    private final IncomeRepository tblIncomeRepository;
    private final WorkDetailRepository tblWorkDetailRepository;

    public IncomeResDto.Detail createIncome(IncomeReqDto.Create create) {
        Member tblMember = memberRepository.getTblMember(securityUtil.getTblMemberId());
        log.debug("######## tblMember : {}", tblMember.getName());

        int days = create.getFromWorkDay().until(create.getToWorkDay()).plusDays(1L).getDays();

        // 기초 정보 저장
        Income tblIncome = tblIncomeRepository.save(create.toEntity(tblMember));

        // 급여 정보 생성
        List<WorkDetail> workDetailSaveData = new ArrayList<>();
        List<LocalDate> workDays = create.getWorkDays();
        if (!CollectionUtils.isEmpty(workDays)) {
            workDays.forEach(wd -> workDetailSaveData.add(WorkDetail.builder()
                    .koreaSalary(create.getKoreaSalary())
                    .workDay(wd)
                    .workHour(create.getWorkHour())
                    .tblIncome(tblIncome)
                    .build())
            );
        }
        /*IntStream.range(0, days).forEach(day -> workDetailSaveData.add(TblWorkDetail.builder()
                .koreaSalary(create.getKoreaSalary())
                .workDay(create.getFromWorkDay().plusDays(day))
                .workHour(create.getWorkHour())
                .tblIncome(tblIncome)
                .build()));*/

        if (!CollectionUtils.isEmpty(workDetailSaveData)) {
            tblIncome.addWorkDetails(tblWorkDetailRepository.saveAll(workDetailSaveData));
        }

        return IncomeResDto.Detail.of(tblIncome);
    }

    public List<IncomeResDto.Detail> listIncome() {
        Member tblMember = memberRepository.getTblMember(securityUtil.getTblMemberId());
        log.debug("######## tblMember : {}", tblMember.getName());

        List<Income> tblIncomes = tblIncomeRepository.getAllByTblMember(tblMember);
        return tblIncomes.stream()
                .map(IncomeResDto.Detail::of)
                .collect(Collectors.toList());
    }

    public IncomeResDto.Detail detailIncome(Long tblIncomeId, OrderType orderType) {
        log.debug("######## tblIncomeId : {}", tblIncomeId);
        return IncomeResDto.Detail.of(tblIncomeRepository.getTblIncome(tblIncomeId), orderType);
    }

    @Transactional
    public IncomeResDto.Detail updateIncome(IncomeReqDto.Update update) {
        log.debug("######## TblIncomeReq.Update : {}", update.getTblIncomeId());
        Income tblIncome = tblIncomeRepository.getTblIncome(update.getTblIncomeId());
        tblIncome.use(update.getUseYn());
        return IncomeResDto.Detail.of(tblIncome);
    }

    @Transactional
    public String deleteIncome(Long tblIncomeId) {
        log.debug("######## tblIncomeId : {}", tblIncomeId);
        Income tblIncome = tblIncomeRepository.getTblIncome(tblIncomeId);
        tblWorkDetailRepository.deleteAll(tblIncome.getWorkDetails());
        tblIncomeRepository.delete(tblIncome);
        return "급여 정보가 삭제 되었습니다.";
    }

    /*public List<TblWorkDetailRes.Detail> listWorkDetail(Long tblIncomeId, String orderValue) {
        log.debug("######## tblIncomeId : {}", orderValue);
        TblIncome tblIncome = tblIncomeRepository.getTblIncome(tblIncomeId);
        tblIncome.getWorkDetails();
        return allOrderBy.stream()
                .filter(Objects::nonNull)
                .map(TblIncomeRes.Detail::of)
                .collect(Collectors.toList());
    }*/
}
