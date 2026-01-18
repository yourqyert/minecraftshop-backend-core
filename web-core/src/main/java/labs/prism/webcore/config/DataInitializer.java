package labs.prism.webcore.config;

import labs.prism.webcore.domain.entity.Admin;
import labs.prism.webcore.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (adminRepository.count() == 0) {
            Admin defaultAdmin = Admin.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123")) // Стандартный пароль для первой установки
                    .role("ADMIN")
                    .build();
            adminRepository.save(defaultAdmin);
            System.out.println(">>> Default admin created: admin / admin123");
        }
    }
}