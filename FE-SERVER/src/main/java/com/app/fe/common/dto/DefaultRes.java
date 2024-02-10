package com.app.fe.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Slf4j
@Data
@Builder
@AllArgsConstructor
public class DefaultRes<REQ, RES> {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private int status;

    private String errorCode;

    private String error;

    private REQ request;

    private RES response;

    private String path;

    /*@Builder
    public DefaultRes(BadRequestException exception) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.errorCode = exception.getErrorCode();
        this.error = exception.getError();
        this.response = (RES) exception.getDetail();
    }

    @Builder
    public DefaultRes(Exception exception, HttpStatus status) {
        this.status = status.value();
        this.errorCode = "";
        this.error = exception.getMessage();
    }*/

    @Builder
    public DefaultRes(REQ requset, RES response) {
        this.status = HttpStatus.OK.value();
        this.response = response;
        this.request = requset;
    }
}
