package com.example.Article;

import org.springframework.data.repository.CrudRepository;
import com.example.Article.ArticleDto;
public interface ArticleRepository extends CrudRepository<Article, Long> {

}
