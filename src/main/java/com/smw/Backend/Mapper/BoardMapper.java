package com.smw.Backend.Mapper;

import com.smw.Backend.domain.BoardRequest;
import com.smw.Backend.domain.BoardResponse;
import com.smw.Backend.domain.BoardSearchCond;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    void save(BoardRequest boardRequest);

    BoardResponse findById(Long id);

    List<BoardResponse> findAll(BoardSearchCond boardSearchCond);

    int count(BoardSearchCond boardSearchCond);

    void update(@Param("id") Long id, @Param("boardRequest") BoardRequest boardRequest);

    void delete(Long id);

    void readCnt(Long id);
}
