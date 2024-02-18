package com.app.fe.member.service;

import com.app.fe.member.dto.MemberReq;
import com.app.fe.member.dto.MemberRes;
import com.app.fe.member.entity.Member;
import com.app.fe.member.repository.MemberRepository;
import com.app.fe.common.util.SecurityUtil;
import com.app.fe.nation.entity.Nation;
import com.app.fe.nation.repository.NationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = false)
@Service
public class MemberService {

    private final SecurityUtil securityUtil;
    private final MemberRepository tblMemberRepository;
    private final NationRepository tblNationRepository;

    public MemberRes.Detail detail(Long tblMemberId) {
        return MemberRes.Detail.of(tblMemberRepository.getTblMember(tblMemberId));
    }

    public MemberRes.Detail myInfo() {
        Member currentTblMember = securityUtil.getCurrentTblMember();
        return MemberRes.Detail.of(currentTblMember);
    }

    @Transactional
    public MemberRes.Detail update(MemberReq.Update update) {
        Member tblMember = tblMemberRepository.getTblMember(update.getTblMemberId());
        Nation tblNation = tblNationRepository.getTblNation(update.getTblNationId());
        tblMember.updateNation(tblNation);
        return MemberRes.Detail.of(tblMember);
    }
}
