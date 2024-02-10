package com.app.fe.auth;

import com.app.fe.auth.service.AuthService;
import com.app.fe.common.dto.DefaultRes;
import com.app.fe.common.dto.TokenDto;
import com.app.fe.common.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// http://localhost:8080/oauth2/authorization/google

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Value("${app.auth.token.refresh-cookie-key}")
    private String cookieKey;

    private final AuthService authService;

    @GetMapping("/token")
    public DefaultRes<String, String> index(@RequestParam("accessToken") String accessToken, HttpServletRequest request) {
        String refreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue).orElseThrow(() -> new RuntimeException("no Refresh Token Cookie"));

        log.debug("######### accessToken : {}", accessToken);
        log.debug("######### refreshToken : {}", refreshToken);
        return new DefaultRes<>("액세스 토큰 : " + accessToken, "리프레시 토큰 : " + refreshToken);
    }

    @PostMapping("/refresh")
    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response, @RequestBody TokenDto tokenDto) {
        return ResponseEntity.ok().body(authService.refreshToken(request, response, tokenDto.getAccessToken()));
    }

    @GetMapping("/logout")
    public DefaultRes<String, String> logout() {
        return new DefaultRes<>(null, "로그아웃 완료.");
    }
}

