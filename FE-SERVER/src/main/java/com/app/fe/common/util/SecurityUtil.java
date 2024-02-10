package com.app.fe.common.util;

import com.app.fe.common.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SecurityUtil {

    public Long getTblMemberId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return customUserDetails.getId();
    }
}
