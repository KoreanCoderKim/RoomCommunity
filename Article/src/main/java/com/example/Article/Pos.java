package com.example.Article;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@Entity
public class Pos {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String RoomId;

    public Pos(Long id, String RoomId) {
        this.id = id;
        this.RoomId = RoomId;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "id=" + id +
                ", RoomId='" + RoomId + '\'' +
                '}';
    }
}
