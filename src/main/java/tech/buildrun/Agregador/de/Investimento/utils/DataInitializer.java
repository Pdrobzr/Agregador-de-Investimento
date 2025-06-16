package tech.buildrun.Agregador.de.Investimento.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.buildrun.Agregador.de.Investimento.entity.Role;
import tech.buildrun.Agregador.de.Investimento.entity.User;
import tech.buildrun.Agregador.de.Investimento.repository.RoleRepository;
import tech.buildrun.Agregador.de.Investimento.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            if (roleRepository.findByRoleName("ADMIN") == null) {
                Role adminRole = roleRepository.save(new Role(1,"ADMIN"));

                userRepository.save(new User("ADMINISTRADOR", "admin@admin", passwordEncoder.encode("123"), adminRole));
            }
            if (roleRepository.findByRoleName("USER") == null) {
                roleRepository.save(new Role(2, "USER"));
            }
        };
    }
}
