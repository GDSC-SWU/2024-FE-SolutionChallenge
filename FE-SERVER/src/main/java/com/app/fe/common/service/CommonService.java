package com.app.fe.common.service;

import com.app.fe.common.dto.CodeRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = false)
@Service
public class CommonService {

    public CodeRes.Item list() {
        return CodeRes.Item.of();
    }
}
