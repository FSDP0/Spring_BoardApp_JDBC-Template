package com.boardapp.boardfe.board.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.boardapp.boardfe.board.model.Board;
import com.boardapp.boardfe.board.service.BoardService;
import com.boardapp.boardfe.common.utils.PagerInfo;

@Controller
// Controller Route Path Mapping
@RequestMapping("boards")
public class BoardViewController {

    // Constructor Injection
    private final BoardService boardService;

    private BoardViewController(BoardService boardService) {
        this.boardService = boardService;
    }
    //

    @GetMapping("/list")
    public String list(PagerInfo pagerInfo, Model model) {
        List<Board> boardList = this.boardService.findAllBoard();

        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        return "board/list";
    }

    @GetMapping("/view")
    public String view(@RequestParam Long num, Model model) {
        Board board = this.boardService.findByBoardId(num);

        model.addAttribute("board", board);

        return "board/view";
    }

    @GetMapping("/write")
    public String write() {
        return "board/form";
    }

    @GetMapping("/modifyForm")
    public String edit(@RequestParam Long num, Model model) {
        Board board = this.boardService.findByBoardId(num);

        model.addAttribute("board", board);

        return "board/form";
    }

    // Post form data transfer content-type : application/x-www-form-urlencoded
    // Query Parameter
    @PostMapping(value = "/writeSubmit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String writeSubmit(Board boardDto, PagerInfo pagerInfo, Model model) {
        this.boardService.createBoard(boardDto);

        List<Board> boardList = this.boardService.findAllBoard();

        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        // Redirect url
        return "redirect:/boards/list";
    }

    @PostMapping(value = "/modifySubmit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String modifySubmit(Board boardDto, PagerInfo pagerInfo, Model model) {
        this.boardService.editBoard(boardDto);

        List<Board> boardList = this.boardService.findAllBoard();

        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        // Redirect url
        return "redirect:/boards/list";
    }


    @GetMapping("/delete")
    public String del(@RequestParam Long num, PagerInfo pagerInfo, Model model) {
        this.boardService.deleteBoard(num);

        List<Board> boardList = this.boardService.findAllBoard();

        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        // Redirect url
        return "redirect:/boards/list";
    }
}
