package com.smw.Backend.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PagingResponse<T> {

    private List<T> list = new ArrayList<>();
    private Paging paging;

    public PagingResponse(List<T> list, Paging paging) {
        this.list = list;
        this.paging = paging;
    }
}
