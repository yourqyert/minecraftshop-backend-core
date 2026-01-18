package labs.prism.webcore.repository;

import labs.prism.webcore.domain.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    Optional<Server> findBySecretKey(String secretKey);
}