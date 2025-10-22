package org.example.miniproject.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.example.miniproject.board.domain.Board;

import java.time.format.DateTimeFormatter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class BoardRequestDto {

    @NotBlank( message = "이름을 입력해주세요")
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank(message = "제목을 입력해주세요")
    @Size(min = 1, max = 100)
    private String title;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 1, max = 100)
    private String password;

    @NotBlank
    private String content;

    public static BoardRequestDto from(Board board) {

        return BoardRequestDto.builder()
                .name(board.getName())
                .title(board.getTitle())
                .password(board.getPassword())
                .content(board.getContent())
                .build();
    }

    public static BoardRequestDto createEmptyRequestDto(){
        return BoardRequestDto.builder()
                .name("")
                .title("")
                .password("")
                .content("")
                .build();
    }
}
