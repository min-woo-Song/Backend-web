package com.smw.Backend.controller;

import com.smw.Backend.domain.*;
import com.smw.Backend.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시판 목록 페이지
    @GetMapping("/list")
    public String boardList(@ModelAttribute("boardSearch") BoardSearchCond boardSearchCond, Model model) {
        List<BoardResponse> boardList = boardService.findAll(boardSearchCond).getList();
        Paging paging = boardService.findAll(boardSearchCond).getPaging();

        model.addAttribute("board", boardList);
        model.addAttribute("paging", Objects.requireNonNullElseGet(paging,
                () -> new Paging(boardService.count(boardSearchCond), boardSearchCond)));

        return "/board/list";
    }

    // 게시글 뷰 페이지
    @GetMapping("/view/{id}")
    public String board(@PathVariable Long id, Model model) {
        BoardResponse board = boardService.findById(id);
        boardService.readCnt(board.getId()); // 조회수 증가
        model.addAttribute("board", board);
        return "/board/view";
    }

    // 게시글 쓰기 폼
    @GetMapping("/write")
    public String writeForm() {
        return "/board/writeForm";
    }

    // 게시글 쓰기 post
    @PostMapping("/write")
    public String writeBoard(@ModelAttribute BoardRequest boardRequest, RedirectAttributes redirectAttributes, Principal principal) {
        boardRequest.setUsername(principal.getName());

        Long boardId = boardService.save(boardRequest);
        redirectAttributes.addAttribute("boardId", boardId);
        return "redirect:/board/view/{boardId}";
    }

    // 게시글 수정 폼
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        BoardResponse board = boardService.findById(id);
        model.addAttribute("board", board);
        return "/board/editForm";
    }

    // 게시글 수정 post
    @PostMapping("/edit/{id}")
    public String editBoard(@PathVariable Long id, @ModelAttribute BoardRequest boardRequest, Principal principal, Model model) {
        boardService.update(id, boardRequest);
        return "redirect:/board/view/{id}";
    }

    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }
}
