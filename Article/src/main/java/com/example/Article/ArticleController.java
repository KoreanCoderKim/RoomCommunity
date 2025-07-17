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
        // 최신 Pos 하나 락 걸고 가져오기

        // 만약 해당 roomId가 아직 없으면 저장 (중복 방지)
        if (!posRepository.existsByRoomId(roomId)) {
            posRepository.save(new Pos(null, roomId));  // pos.getId() 대신 null (보통 ID는 자동 생성)
        }
        // 게시글 저장
        Article article = form.toEntity(roomId);
        articleRepository.save(article);

        model.addAttribute("Id", lastPos.getRoomId());
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