package com.smw.Backend.service;

import com.smw.Backend.Mapper.BoardMapper;
import com.smw.Backend.domain.board.BoardRequest;
import com.smw.Backend.domain.board.BoardResponse;
import com.smw.Backend.domain.board.BoardSearchCond;
import com.smw.Backend.domain.member.Member;
import com.smw.Backend.domain.paging.Paging;
import com.smw.Backend.domain.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Member member = authCheck();
        BoardResponse boardId = findById(id);
        if (member.getNickname().equals(boardId.getNickname()))
            boardMapper.update(id, boardRequest);
        else
            throw new RuntimeException("계정이 일치하지 않습니다.");
    }

    @Transactional
    public void delete(Long id) {
        Member member = authCheck();
        BoardResponse boardId = findById(id);
        if (member.getNickname().equals(boardId.getNickname()))
            boardMapper.delete(id);
        else
            throw new RuntimeException("계정이 일치하지 않습니다.");
    }

    public void readCnt(Long id) {
        boardMapper.readCnt(id);
    }

    public Member authCheck() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Member) authentication.getPrincipal();
    }
}
