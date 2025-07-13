package com.example.Article;
import com.example.Article.ArticleDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String RoomId;
    @Column
    private String News;
    public Article(Long id, String RoomId, String title, String News) {
        this.id = id;
        this.News = News;
        this.title = title;
        this.RoomId = RoomId;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", RoomId='" + RoomId + '\'' +
                ", News='" + News + '\'' +
                '}';
    }
}
