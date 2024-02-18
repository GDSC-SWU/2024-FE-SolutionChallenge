package com.app.fe.common.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageModel {

    private int page;
    private int size;
    private Sort.Direction direction;
    private String orderFieldName;

    public int getPage() {
        return this.page = Math.max(page, 0);
    }

    public int getSize() {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50000;
        return this.size = size > MAX_SIZE || size <= 0 ? DEFAULT_SIZE : size;
    }

    public Sort.Direction getDirection() {
        if (direction == null) {
            return Sort.Direction.DESC;
        }
        return this.direction;
    }

    public PageRequest of(String... properties) {
        return PageRequest.of(getPage() - 1, getSize(), getDirection(), properties);
    }

    public PageRequest lastOne(String... properties) {
        return PageRequest.of(0, 1, Sort.Direction.DESC, properties);
    }

    public PageRequest ofOrderBy(Integer currentPage, Sort.Direction direction, Integer pageSize, String orderProperty) {
        this.page = currentPage;
        return PageRequest.of(getPage(), pageSize, direction, orderProperty);
    }
}
