package com.smw.Backend.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchCond {

    private int page;
    private int recordSize;
    private int pageSize;
    private Paging paging;

    private String option;
    private String keyword;

    public BoardSearchCond() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }
}
