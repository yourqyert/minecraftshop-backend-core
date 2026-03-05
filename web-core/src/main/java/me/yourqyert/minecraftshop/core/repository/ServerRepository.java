package me.yourqyert.minecraftshop.core.repository;

import me.yourqyert.minecraftshop.core.domain.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    Optional<Server> findBySecretKey(String secretKey);
}