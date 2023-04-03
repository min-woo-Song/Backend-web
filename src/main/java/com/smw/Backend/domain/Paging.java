package com.smw.Backend.domain;

import lombok.Getter;

@Getter
public class Paging {
    private int totalRecordCount;
    private int totalPageCount;
    private int startPage;
    private int endPage;
    private int limitStart;
    private boolean existPrevPage;
    private boolean existNextPage;

    public Paging(int totalRecordCount, BoardSearchCond boardSearchCond) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.calculation(boardSearchCond);
        }
    }

    private void calculation(BoardSearchCond boardSearchCond) {

        totalPageCount = ((totalRecordCount - 1) / boardSearchCond.getRecordSize()) + 1;

        if (boardSearchCond.getPage() > totalPageCount) {
            boardSearchCond.setPage(totalPageCount);
        }

        startPage = ((boardSearchCond.getPage() - 1) / boardSearchCond.getPageSize()) * boardSearchCond.getPageSize() + 1;

        endPage = startPage + boardSearchCond.getPageSize() - 1;

        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        limitStart = (boardSearchCond.getPage() - 1) * boardSearchCond.getRecordSize();

        existPrevPage = startPage != 1;

        existNextPage = (endPage * boardSearchCond.getRecordSize()) < totalRecordCount;
    }
}
