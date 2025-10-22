package org.example.miniproject.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.miniproject.board.dto.BoardRequestDto;
import org.example.miniproject.board.dto.BoardResponseDto;
import org.example.miniproject.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller()
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String getBoardList(Model model) {

        List<BoardResponseDto> boardList = boardService.getBoardList();
        model.addAttribute("boards", boardList);

        return "board/list";
    }

    @GetMapping("/view")
    public String getBoardView(@RequestParam Long id, Model model) {

        BoardResponseDto board = boardService.getBoard(id);
        model.addAttribute("board", board);

        return "board/view";
    }

    @GetMapping("/writeform")
    public String getBoardWriteForm(Model model) {

        model.addAttribute("board", BoardRequestDto.createEmptyRequestDto());
        return "board/writeForm";
    }

    @PostMapping("/write")
    public String saveBoard(@ModelAttribute(name = "board") @Valid BoardRequestDto requestDto,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "board/writeForm";
        }

        boardService.createBoard(requestDto);
        return "redirect:/list";
    }

    @GetMapping("/updateform")
    public String getUpdateForm(@RequestParam Long id, Model model) {

        BoardRequestDto board = boardService.getBoardRequestDto(id);
        model.addAttribute("id", id);
        model.addAttribute("board", board);

        return "board/updateForm";
    }

    @PostMapping("/update")
    public String updateBoard(@ModelAttribute(name = "board") @Valid BoardRequestDto requestDto,
                              BindingResult bindingResult,
                              @RequestParam Long id,
                              RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("error Board bindresult {}", bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("id", id);
            return "board/updateForm";
        }

        boardService.updateBoard(id, requestDto);

        redirectAttributes.addAttribute("id", id);
        return "redirect:/view";
    }

    @GetMapping("/deleteform")
    public String getDeleteForm(@RequestParam Long id, Model model) {
        BoardResponseDto board = boardService.getBoard(id);
        model.addAttribute("id", id);

        return "board/deleteForm";
    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam Long id, @RequestParam String password) {

        boardService.deleteBoard(id, password);

        return "redirect:/list";
    }


}
