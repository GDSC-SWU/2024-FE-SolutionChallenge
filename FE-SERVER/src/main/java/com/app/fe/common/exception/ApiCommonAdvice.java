package com.app.fe.common.exception;

import com.app.fe.common.dto.DefaultRes;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(value = 1)
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiCommonAdvice {

    private final HttpServletRequest httpServletRequest;

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<DefaultRes<String, String>> unauthorizedException(Exception e) {
        log.debug("####### @ExceptionHandler({AccessDeniedException.class})");
        log.error(e.getMessage(), e);
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        DefaultRes<String, String> defaultRes = new DefaultRes<>(e, httpStatus);
        defaultRes.setPath(httpServletRequest.getRequestURI());
        return new ResponseEntity<>(defaultRes, httpStatus);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<DefaultRes<String, String>> handleException(Exception e) {
        log.debug("####### @ExceptionHandler({Exception.class})");
        log.error(e.getMessage(), e);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        DefaultRes<String, String> defaultRes = new DefaultRes<>(e, httpStatus);
        defaultRes.setPath(httpServletRequest.getRequestURI());
        return new ResponseEntity<>(defaultRes, httpStatus);
    }
}
