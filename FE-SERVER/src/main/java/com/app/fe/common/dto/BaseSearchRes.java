package com.app.fe.common.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BaseSearchRes {

    private final long totalElements;

    private final int totalPages;

    private final int pageNumber;

    private final int numberOfElements;

    private final int pageSize;

    private final boolean first;

    private final boolean empty;

    private final boolean last;

    private final int pageStartNumber;

    private final int pageEndNumber;

    public BaseSearchRes(Page<?> page) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageNumber = page.getNumber() + 1;
        this.numberOfElements = page.getNumberOfElements();
        this.pageSize = page.getSize();
        this.first = page.isFirst();
        this.empty = page.isEmpty();
        this.last = page.isLast();

        int pagingCount = 10;
        int pagingBeforeCount = 4;
        int pagingAfterCount = pagingCount - pagingBeforeCount - 1;

        if (pageNumber <= pagingAfterCount) {
            this.pageStartNumber = 1;
        } else if (totalPages - (pagingBeforeCount + pagingAfterCount) <= 0) {
            this.pageStartNumber = 1;
        } else if (pageNumber + pagingAfterCount >= totalPages) {
            this.pageStartNumber = totalPages - (pagingBeforeCount + pagingAfterCount);
        } else {
            this.pageStartNumber = pageNumber - pagingBeforeCount;
        }

        if (totalPages < 10) {
            this.pageEndNumber = totalPages;
        } else if (pageNumber + pagingAfterCount <= totalPages) {
            this.pageEndNumber = pageStartNumber + pagingBeforeCount + pagingAfterCount;
        } else {
            this.pageEndNumber = totalPages;
        }
    }
}
