package com.vlad.controllers;

import com.vlad.news.dto.NewsDTO;
import com.vlad.services.NewsCRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;


@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsCRUDService newsService;

    public NewsController(NewsCRUDService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        NewsDTO news = newsService.getById(id);

        if (news == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "News with id " + id + " not found"));
        }
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public Collection<NewsDTO> getAllNews() {
        return newsService.getAll();
    }

    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) {
        NewsDTO news = newsService.create(newsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(news);
    }

    @PutMapping()
    public ResponseEntity<?> updateNews(@RequestBody NewsDTO newsDTO) {
        Long id = newsDTO.getId();
        if (id == null || newsService.getById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "News with id " + id + " not found"));
        }
        newsService.update(id, newsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newsDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        NewsDTO news = newsService.getById(id);
        if (news == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "News with id " + id + " not found"));
        }
            newsService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
