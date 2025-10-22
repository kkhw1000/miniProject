package org.example.miniproject.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.example.miniproject.board.dto.BoardRequestDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@Table(name = "BOARD")
public class Board {

    @Id
    private Long id;

    @Column("NAME")
    private String name;

    @Column("TITLE")
    private String title;

    @Column("CONTENT")
    private String content;

    @Column("PASSWORD")
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static Board create(BoardRequestDto dto) {

        return Board.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public boolean isPasswordCorrect(String inputPassword) {
        return password.equals(inputPassword);
    }

    public void update(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

}
