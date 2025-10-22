package org.example.miniproject.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.miniproject.board.Repository.BoardRepository;
import org.example.miniproject.board.domain.Board;
import org.example.miniproject.board.dto.BoardRequestDto;
import org.example.miniproject.board.dto.BoardResponseDto;
import org.example.miniproject.exception.BoardNotFoundException;
import org.example.miniproject.exception.PasswordMismatchException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponseDto> getBoardList() {

        List<Board> boards = boardRepository.findAllByOrderByIdDesc();
        List<BoardResponseDto> boardResponseDtos = boards.stream().map(BoardResponseDto::from).toList();

        return boardResponseDtos;
    }

    public BoardResponseDto getBoard(Long id) {

        Optional<Board> byId = boardRepository.findById(id);
        if (byId.isEmpty()) throw new BoardNotFoundException("Board not found");

        BoardResponseDto boardResponseDto = BoardResponseDto.from(byId.get());

        return boardResponseDto;
    }

    public BoardRequestDto getBoardRequestDto(Long id) {
        Optional<Board> byId = boardRepository.findById(id);
        if (byId.isEmpty()) throw new BoardNotFoundException("Board not found");
        BoardRequestDto boardRequestDto = BoardRequestDto.from(byId.get());
        return boardRequestDto;
    }

    @Transactional(readOnly = false)
    public Long createBoard(BoardRequestDto dto) {

        Board board = Board.create(dto);
        Board saved = boardRepository.save(board);

        return saved.getId();
    }

    @Transactional(readOnly = false)
    public void updateBoard(Long id, BoardRequestDto dto) {

        Optional<Board> byId = boardRepository.findById(id);
        if (byId.isEmpty()) throw new BoardNotFoundException("Board not found");

        Board board = boardRepository.findById(id).get();
        if (!board.isPasswordCorrect(dto.getPassword())) throw new PasswordMismatchException("Password mismatch");

        log.info("pre Board value {},{},{}", board.getName(),board.getTitle(),board.getContent());
        board.update(dto.getName(), dto.getTitle(), dto.getContent());
        log.info("updated Board value {},{},{}", dto.getName(),dto.getTitle(),dto.getContent());
        boardRepository.save(board);
    }

    @Transactional(readOnly = false)
    public void deleteBoard(Long id, String password) {

        Optional<Board> byId = boardRepository.findById(id);
        if (byId.isEmpty()) throw new BoardNotFoundException("Board not found");

        Board board = boardRepository.findById(id).get();
        if (!board.isPasswordCorrect(password)) throw new PasswordMismatchException("Password mismatch");

        boardRepository.deleteById(id);

    }

}
