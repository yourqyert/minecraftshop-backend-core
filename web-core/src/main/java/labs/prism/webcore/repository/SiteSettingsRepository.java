package labs.prism.webcore.repository;

import labs.prism.webcore.domain.entity.SiteSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для управления глобальными настройками движка.
 * Поскольку настройки всегда существуют в единственном экземпляре,
 * взаимодействие обычно происходит только с ID = 1.
 */
@Repository
public interface SiteSettingsRepository extends JpaRepository<SiteSettings, Long> {
}