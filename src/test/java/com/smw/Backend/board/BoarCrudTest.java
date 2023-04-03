package com.smw.Backend.board;

import com.smw.Backend.domain.BoardRequest;
import com.smw.Backend.domain.BoardResponse;
import com.smw.Backend.domain.BoardSearchCond;
import com.smw.Backend.domain.Paging;
import com.smw.Backend.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class BoarCrudTest {

    @Autowired
    private BoardService boardService;

    @Test
    void save() {
        BoardRequest boardRequest = new BoardRequest("TestUser","title", "content");
        boardService.save(boardRequest);

        BoardResponse findId = boardService.findById(boardRequest.getId());
        assertThat(boardRequest.getTitle()).isEqualTo(findId.getTitle());
    }

    @Test
    void findById() {
        BoardResponse findId = boardService.findById(1L);
        assertThat(findId.getTitle()).isEqualTo("FirstTitle");
    }

    @Test
    void findAll() {
        BoardSearchCond boardSearchCond = new BoardSearchCond();
        boardSearchCond.setKeyword("first");
        boardSearchCond.setOption("all");
        List<BoardResponse> list = boardService.findAll(boardSearchCond).getList();
        Paging paging = boardService.findAll(boardSearchCond).getPaging();
        System.out.println(paging.getTotalRecordCount());
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void update() {
        BoardRequest boardRequest = new BoardRequest("Third","title3", "content3");
        boardService.update(3L, boardRequest);

        BoardResponse findId = boardService.findById(3L);
        assertThat(findId.getTitle()).isEqualTo("title3");
    }

    @Test
    void delete() {
        boardService.delete(1L);

        BoardResponse findId = boardService.findById(3L);

        assertThat(findId.getIsView()).isEqualTo("1");
    }

    @Test
    @Commit
    void dummyData() {
        BoardRequest boardRequest;
        String user = "user";
        String title = "title";
        String content = "content";

        for (int i = 200; i < 250; i++) {
            boardService.save(new BoardRequest(user+i, title+i, content+i));
        }
    }
}
