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
    @GetMapping("/Made")
    public String Make() {
        return "name";
    }
    @PostMapping("/home")
    public String made(PosDto form) {
        if (!posRepository.existsByRoomId(form.getRoomId())) {
            posRepository.save(new Pos(null, form.getRoomId()));  // pos.getId() 대신 null (보통 ID는 자동 생성)
        }
        return "redirect:/index?RoomId="+form.getRoomId();
    }
    @GetMapping("/In")
    public String RoomIn() {
        return "main";
    }
    @PostMapping("/Process")
    public String GO(PosDto form) {
        if (posRepository.existsByRoomId(form.getRoomId())) {
            return "redirect:/index?RoomId="+form.getRoomId();
        }
        return "redirect:/In";
    }
    @GetMapping("/index")
    public String App(Model model, @RequestParam String RoomId) {
        model.addAttribute("Id", RoomId);
        return "index";
    }
    @GetMapping("/new")
    public String Write(Pos pos, @RequestParam String RoomId, Model model) {
        model.addAttribute("Id",RoomId);
        return "Wrote";
    }
    @PostMapping("/RoomCommunity")
    public String OpenRoom(@RequestParam String roomId, Model model, ArticleDto form) {
        // 게시글 저장
        Article article = form.toEntity(roomId);
        articleRepository.save(article);

        model.addAttribute("Id", roomId);
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