package com.example.Article;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    List<Article> findByRoomId(String roomId);  // 다수의 게시글을 가져오기 위함
}
