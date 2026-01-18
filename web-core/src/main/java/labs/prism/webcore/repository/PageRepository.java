package labs.prism.webcore.repository;

import labs.prism.webcore.domain.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    Optional<Page> findBySlug(String slug); // Поиск страницы для отображения на фронте
}