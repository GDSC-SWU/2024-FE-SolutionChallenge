package com.app.fe.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class BaseSearchReq {
    private int maxMonthPeriodGap = 3;
    private int currentPage = 1;
    private int pageSize = 10;
    private String orderProperty = "createdDate";
    private Sort.Direction direction = Sort.Direction.DESC;

    private String searchDateType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate searchFromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate searchToDate;

    private Long createdEmpId;
    private Long updatedEmpId;

    public String getSearchFromDateToString() {
        return searchFromDate == null ? null : searchFromDate.toString();
    }

    public String getSearchToDateToString() {
        return searchToDate == null ? null : searchToDate.toString();
    }

    public void setCurrentPageNumber(int current) {
        this.currentPage = current;
    }

    public void validate() {
        if (Objects.isNull(searchDateType)) {
            searchDateType = "createDate";
        }

        if (Objects.isNull(searchToDate) || Objects.isNull(searchFromDate)) {
            searchToDate = LocalDate.now();
            searchFromDate = searchToDate.minusMonths(maxMonthPeriodGap);
        }

        if (searchFromDate.isAfter(LocalDate.now())) {
            throw new DateTimeException("시작 날짜가 현재 날짜보다 미래입니다.");
        }

        /*if (searchToDate.isAfter(LocalDate.now())) {
            throw new DateTimeException("종료 날짜가 현재 날짜보다 미래 입니다.");
        }*/

        if (searchFromDate.isAfter(searchToDate)) {
            throw new DateTimeException("시작 날짜가 종료 날짜보다 미래일 수 없습니다.");
        }

//        if (searchToDate.minusMonths(maxMonthPeriodGap).isAfter(searchFromDate)) {
//            throw new DateTimeException("최대 3개월 까지 검색 가능 합니다.");
//        }
    }

    public Pageable ofPageble() {
        validate();
        return PageRequest.of(this.currentPage <= 1 ? 0 : currentPage - 1, this.pageSize, this.direction, orderProperty);
    }
}
