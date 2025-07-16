package com.example.Article;

import com.example.Article.ArticleController;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class ArticleDto {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String News;

    public ArticleDto(String title, String News) {
        this.title = title;
        this.News = News;
    }

    public Article toEntity(String Data) {
        return new Article(null, Data, title, News);
    }
}
