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

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +

                ", title='" + title + '\'' +
                ", News='" + News + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null, ArticleController.MyId, title, News);
    }
}
