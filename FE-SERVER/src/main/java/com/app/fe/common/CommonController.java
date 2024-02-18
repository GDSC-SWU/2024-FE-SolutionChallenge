package com.app.fe.common;

import com.app.fe.common.dto.CodeRes;
import com.app.fe.common.dto.DefaultRes;
import com.app.fe.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/common")
@RestController
public class CommonController {

    private final CommonService commonService;

    @GetMapping("/list")
    public DefaultRes<?, CodeRes.Item> list() {
        return new DefaultRes<>(null, commonService.list());
    }

    @GetMapping("/test")
    public String test(){
        return "테스트 완료";
    }
}
