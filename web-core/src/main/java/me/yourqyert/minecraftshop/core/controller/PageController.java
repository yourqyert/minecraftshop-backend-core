package me.yourqyert.minecraftshop.core.controller;

import me.yourqyert.minecraftshop.core.domain.entity.Page;
import me.yourqyert.minecraftshop.core.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pages")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @GetMapping
    public ResponseEntity<List<Page>> getAll() {
        return ResponseEntity.ok(pageService.getAllPages());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Page> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(pageService.getPageBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<Page> create(@RequestBody Page page) {
        return ResponseEntity.ok(pageService.savePage(page));
    }
}