package com.example.Article;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private PosRepository posRepository;
    @GetMapping("/In")
    public String RoomIn() {
        return "main";
    }
    @PostMapping("/Process")
    public String GO(PosDto form) {
        return "redirect:/index?RoomId="+form.getRoomId();
    }
    @GetMapping("/index")
    public String App(Pos pos, Model model, @RequestParam String RoomId) {
        model.addAttribute("Id", RoomId);
        if (!posRepository.existsByRoomId(RoomId)) {
           posRepository.save(new Pos(pos.getId(), RoomId));
        }
        return "index";
    }
    @GetMapping("/new")
    public String Write(Pos pos, @RequestParam String RoomId, Model model) {
        model.addAttribute("Id",RoomId);
        return "Wrote";
    }
    @PostMapping("/RoomCommunity")
    @Transactional
    public String OpenRoom(Pos pos,Model model, ArticleDto form) {
        Pageable pageable = PageRequest.of(0, 1);
        List<Pos> results = posRepository.findTopWithLock(pageable);
        Pos lastPos = results.isEmpty() ? null : results.get(0);
        Article article = form.toEntity(lastPos.getRoomId());
        articleRepository.save(article);
        model.addAttribute("Id",form.toEntity(lastPos.getRoomId()).getRoomId());// 데이터베이스에 저장
        return "index";
    }
    // 방 검색
    @GetMapping("/Search")
    public String Open(@RequestParam String RoomId, Model model) {
       List<Article> results = articleRepository.findByRoomId(RoomId);
       List<String> li = new ArrayList<>();
       for (Article article : results) {
          li.add(article.getRoomId() + " | " + article.getTitle() + " | " + article.getNews());
       }
       model.addAttribute("Data", li);
       model.addAttribute("Id", RoomId);
       return "Show";
    }
}