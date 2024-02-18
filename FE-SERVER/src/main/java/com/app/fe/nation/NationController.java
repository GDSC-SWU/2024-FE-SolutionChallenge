package com.app.fe.nation;

import com.app.fe.nation.dto.NationRes;
import com.app.fe.common.dto.DefaultRes;
import com.app.fe.nation.service.NationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/nation")
@RestController
public class NationController {
    private final NationService tblNationService;

    @GetMapping("/list")
    public DefaultRes<?, List<NationRes.Detail>> listNation() {
        return new DefaultRes<>(null, tblNationService.list());
    }
}
