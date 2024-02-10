package com.app.fe.member;

import com.app.fe.common.dto.DefaultRes;
import com.app.fe.member.dto.MemberReqDto;
import com.app.fe.member.dto.MemberResDto;
import com.app.fe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@RestController
public class MemberController {

    private final MemberService tblMemberService;

    @GetMapping("/detail/{tblMemberId}")
    public DefaultRes<String, MemberResDto.Detail> detail(
            @PathVariable(value = "tblMemberId") Long tblMemberId) {
        return new DefaultRes<>(tblMemberId.toString(), tblMemberService.detail(tblMemberId));
    }

    @PostMapping("/update")
    public DefaultRes<MemberReqDto.Update, MemberResDto.Detail> detail(@RequestBody MemberReqDto.Update update) {
        return new DefaultRes<>(update, tblMemberService.update(update));
    }
}
