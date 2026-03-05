package me.yourqyert.minecraftshop.core.controller;

import me.yourqyert.minecraftshop.core.domain.entity.SiteSettings;
import me.yourqyert.minecraftshop.core.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping("/public")
    public ResponseEntity<SiteSettings> getPublicSettings() {
        return ResponseEntity.ok(settingsService.getSettings());
    }

    @PostMapping("/admin/update")
    public ResponseEntity<SiteSettings> updateSettings(@RequestBody SiteSettings settings) {
        return ResponseEntity.ok(settingsService.updateSettings(settings));
    }
}