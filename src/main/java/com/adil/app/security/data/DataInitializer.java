package com.adil.app.security.data;

import com.adil.app.security.domain.AppUser;
import com.adil.app.security.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init(AppUserRepository repository) {
        return args -> {
            if (repository.findByUsername("adil").isEmpty()) {
                AppUser user = AppUser.builder()
                        .username("adil")
                        .password(passwordEncoder.encode("adil1234"))
                        .build();
                repository.save(user);
            }
        };
    }
}
