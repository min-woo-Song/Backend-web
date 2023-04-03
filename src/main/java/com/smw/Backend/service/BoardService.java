package com.smw.Backend.service;

import com.smw.Backend.Mapper.BoardMapper;
import com.smw.Backend.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    @Transactional
    public Long save(BoardRequest boardRequest) {
        boardMapper.save(boardRequest);
        return boardRequest.getId();
    }

    public BoardResponse findById(Long id) {
        return boardMapper.findById(id);
    }

    public PagingResponse<BoardResponse> findAll(BoardSearchCond boardSearchCond) {

        int count = boardMapper.count(boardSearchCond);
        if (count < 1)
            return new PagingResponse<>(Collections.emptyList(), null);

        Paging paging = new Paging(count, boardSearchCond);
        boardSearchCond.setPaging(paging);

        List<BoardResponse> list = boardMapper.findAll(boardSearchCond);
        return new PagingResponse<>(list, paging);
    }

    public int count(BoardSearchCond boardSearchCond) {
        return boardMapper.count(boardSearchCond);
    }

    @Transactional
    public void update(Long id, BoardRequest boardRequest) {
        boardMapper.update(id, boardRequest);
    }

    @Transactional
    public void delete(Long id) {
        boardMapper.delete(id);
    }

    public void readCnt(Long id) {
        boardMapper.readCnt(id);
    }
}
