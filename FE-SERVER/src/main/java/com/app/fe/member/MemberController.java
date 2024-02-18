package com.app.fe.member;

import com.app.fe.member.dto.MemberReq;
import com.app.fe.member.dto.MemberRes;
import com.app.fe.common.dto.DefaultRes;
import com.app.fe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api1/v1/member")
@RestController
public class MemberController {

    private final MemberService tblMemberService;

    @GetMapping("/detail/{tblMemberId}")
    public DefaultRes<String, MemberRes.Detail> detail(
            @PathVariable(value = "tblMemberId") Long tblMemberId) {
        return new DefaultRes<>(tblMemberId.toString(), tblMemberService.detail(tblMemberId));
    }

    @GetMapping("/myInfo")
    public DefaultRes<String, MemberRes.Detail> myInfo() {
        return new DefaultRes<>(null, tblMemberService.myInfo());
    }

    @PostMapping("/update")
    public DefaultRes<MemberReq.Update, MemberRes.Detail> detail(@RequestBody MemberReq.Update update) {
        return new DefaultRes<>(update, tblMemberService.update(update));
    }
}
