package com.example.Article;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class PosDto {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String RoomId;

    public PosDto(String RoomId) {
        this.RoomId = RoomId;
    }

    public Pos toEntity() {
        return new Pos(null, RoomId);
    }
}