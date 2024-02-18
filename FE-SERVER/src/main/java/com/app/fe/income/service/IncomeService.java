package com.app.fe.income.service;

import com.app.fe.income.dto.IncomeReq;
import com.app.fe.income.dto.IncomeRes;
import com.app.fe.income.dto.WorkDetailReq;
import com.app.fe.income.entity.Income;
import com.app.fe.income.repository.IncomeRepository;
import com.app.fe.income.code.OrderType;
import com.app.fe.income.entity.WorkDetail;
import com.app.fe.income.repository.WorkDetailRepository;
import com.app.fe.common.util.SecurityUtil;
import com.app.fe.member.entity.Member;
import com.app.fe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = false)
@Service
public class IncomeService {

    private final SecurityUtil securityUtil;
    private final MemberRepository memberRepository;
    private final IncomeRepository tblIncomeRepository;
    private final WorkDetailRepository tblWorkDetailRepository;

    public IncomeRes.Detail createIncome(IncomeReq.Create create) {
        Member currentTblMember = securityUtil.getCurrentTblMember();
        log.debug("######## tblMember : {}", currentTblMember.getName());

        // 기초 정보 저장
        Income tblIncome = tblIncomeRepository.save(create.toEntity(currentTblMember));

        // 급여 정보 생성
        List<WorkDetail> workDetailSaveData = new ArrayList<>();
        List<WorkDetailReq.Create> tblWorks = create.getTblWorks();
        if (!CollectionUtils.isEmpty(tblWorks)) {
            tblWorks.forEach(wd -> workDetailSaveData.add(WorkDetail.builder()
//                    .koreaSalary(tblWorks.get(0).getKoreaSalary())
                    .koreaSalary(wd.getKoreaSalary())
                    .workDay(wd.getWorkDay())
                    .workHour(wd.getWorkHour())
                    .tblIncome(tblIncome)
                    .build())
            );
        }

        if (!CollectionUtils.isEmpty(workDetailSaveData)) {
            tblIncome.addWorkDetails(tblWorkDetailRepository.saveAll(workDetailSaveData));
        }

        return IncomeRes.Detail.of(tblIncome);
    }

    public List<IncomeRes.Detail> listIncome() {
        Member tblMember = memberRepository.getTblMember(securityUtil.getCurrentTblMemberId());
        log.debug("######## tblMember : {}", tblMember.getName());

        List<Income> tblIncomes = tblIncomeRepository.getAllByTblMember(tblMember);
        return tblIncomes.stream()
                .map(IncomeRes.Detail::of)
                .collect(Collectors.toList());
    }

    public IncomeRes.Detail detailIncome(Long tblIncomeId, OrderType orderType) {
        log.debug("######## tblIncomeId : {}", tblIncomeId);
        return IncomeRes.Detail.of(tblIncomeRepository.getTblIncome(tblIncomeId), orderType);
    }

    @Transactional
    public IncomeRes.Detail updateIncome(IncomeReq.Update update) {
        log.debug("######## IncomeReq.Update : {}", update.getTblIncomeId());
        Income tblIncome = tblIncomeRepository.getTblIncome(update.getTblIncomeId());
        tblIncome.use(update.getUseYn());
        return IncomeRes.Detail.of(tblIncome);
    }

    @Transactional
    public String deleteIncome(Long tblIncomeId) {
        log.debug("######## tblIncomeId : {}", tblIncomeId);
        Income tblIncome = tblIncomeRepository.getTblIncome(tblIncomeId);
        tblWorkDetailRepository.deleteAll(tblIncome.getWorkDetails());
        tblIncomeRepository.delete(tblIncome);
        return "급여 정보가 삭제 되었습니다.";
    }

    /*public List<WorkDetailRes.Detail> listWorkDetail(Long tblIncomeId, String orderValue) {
        log.debug("######## tblIncomeId : {}", orderValue);
        Income tblIncome = tblIncomeRepository.getTblIncome(tblIncomeId);
        tblIncome.getWorkDetails();
        return allOrderBy.stream()
                .filter(Objects::nonNull)
                .map(IncomeRes.Detail::of)
                .collect(Collectors.toList());
    }*/
}
