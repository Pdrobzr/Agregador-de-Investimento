package tech.buildrun.Agregador.de.Investimento.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.buildrun.Agregador.de.Investimento.entity.Role;
import tech.buildrun.Agregador.de.Investimento.repository.RoleRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByRoleName("ADMIN") == null) {
                roleRepository.save(new Role("ADMIN"));
            }
            if (roleRepository.findByRoleName("USER") == null) {
                roleRepository.save(new Role("USER"));
            }
        };
    }
}
