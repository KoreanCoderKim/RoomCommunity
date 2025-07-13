package com.example.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    public static String MyId; // 현재 방 위치 저장 공간
    @GetMapping("/In")
    public String RoomIn() {
        return "main";
    }
    @PostMapping("/Process")
    public String GO(PosDto form) {
        MyId = form.toEntity().getRoomId(); // 현재 방위치 설정
        return "index";
    }
    @GetMapping("/new")
    public String Write() {
        return "Wrote";
    }
    @Autowired
    private ArticleRepository articleRepository;
    @PostMapping("/RoomCommunity")
    public String OpenRoom(ArticleDto form) {
        Article article = form.toEntity();

        Article saved = articleRepository.save(article); // 데이터베이스에 저장
        System.out.println(saved.toString());
        return "index";
    }
    // 방 검색
    @GetMapping("/Search")
    public String Read() {
        return "Read";
    }
    @PostMapping("/SearchResult")
    public String Open(Model model, FindDto form) {
        ArrayList<String> li = new ArrayList<>(List.of());
        String target = form.getRoom();
        for (Article object : articleRepository.findAll()) {
            String obj = object.getRoomId();

            if (obj.equals(target)) {
                System.out.println(object);
                li.add(object.getRoomId() + "," + object.getTitle() + "," + object.getNews());
            }
        }
        model.addAttribute("Data", li);
        return "Show";
    }
    // 할 일: 마우스 클릭으로 게시글 보기, 이미지 추가
}