package me.yourqyert.minecraftshop.core.service;

import me.yourqyert.minecraftshop.core.domain.entity.Page;
import me.yourqyert.minecraftshop.core.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;

    public List<Page> getAllPages() {
        return pageRepository.findAll();
    }

    public Page getPageBySlug(String slug) {
        return pageRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Page not found: " + slug));
    }

    @Transactional
    public Page savePage(Page page) {
        return pageRepository.save(page);
    }

    @Transactional
    public void deletePage(Long id) {
        pageRepository.deleteById(id);
    }
}