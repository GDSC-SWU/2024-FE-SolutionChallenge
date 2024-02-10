package com.app.fe.member.service;

import com.app.fe.member.dto.MemberReqDto;
import com.app.fe.nation.entity.Nation;
import com.app.fe.member.dto.MemberResDto;
import com.app.fe.member.entity.Member;
import com.app.fe.member.repository.MemberRepository;
import com.app.fe.nation.repository.NationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository tblMemberRepository;
    private final NationRepository tblNationRepository;

    public MemberResDto.Detail detail(Long tblMemberId) {
        return MemberResDto.Detail.of(tblMemberRepository.getTblMember(tblMemberId));
    }

    @Transactional
    public MemberResDto.Detail update(MemberReqDto.Update update) {
        Member tblMember = tblMemberRepository.getTblMember(update.getTblMemberId());
        Nation tblNation = tblNationRepository.getTblNation(update.getTblNationId());
        tblMember.updateNation(tblNation);
        return MemberResDto.Detail.of(tblMember);
    }
}
