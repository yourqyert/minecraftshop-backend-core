package labs.prism.webcore.service;

import labs.prism.webcore.domain.entity.SiteSettings;
import labs.prism.webcore.repository.SiteSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SettingsService {

    private final SiteSettingsRepository settingsRepository;

    public SiteSettings getSettings() {
        return settingsRepository.findById(1L)
                .orElse(SiteSettings.builder()
                        .serverName("My Minecraft Server")
                        .primaryColor("#ff0000")
                        .build());
    }

    @Transactional
    public SiteSettings updateSettings(SiteSettings newSettings) {
        newSettings.setId(1L);
        return settingsRepository.save(newSettings);
    }
}