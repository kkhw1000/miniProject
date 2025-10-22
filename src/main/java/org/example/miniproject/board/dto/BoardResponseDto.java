package org.example.miniproject.board.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.example.miniproject.board.domain.Board;

import java.time.format.DateTimeFormatter;


@Builder(access = AccessLevel.PRIVATE)
@Getter
public class BoardResponseDto {

    private Long id;
    private String name;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;



    public static BoardResponseDto from(Board board) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return BoardResponseDto.builder()
                .id(board.getId())
                .name(board.getName())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt().format(formatter))
                .updatedAt(board.getUpdatedAt().format(formatter))
                .build();
    }

}
