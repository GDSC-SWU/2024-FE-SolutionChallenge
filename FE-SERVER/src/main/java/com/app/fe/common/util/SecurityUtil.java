package com.app.fe.common.util;

import com.app.fe.common.model.CustomUserDetails;
import com.app.fe.member.entity.Member;
import com.app.fe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SecurityUtil {

    private final MemberRepository memberRepository;

    public Member getCurrentTblMember() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        return memberRepository.getTblMember(customUserDetails.getId());
    }

    public Long getCurrentTblMemberId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        return memberRepository.getTblMember(customUserDetails.getId()).getTblMemberId();
    }
}
