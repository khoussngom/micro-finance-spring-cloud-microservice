package com.khouss.compte.seeders;

import com.khouss.compte.entities.Compte;
import com.khouss.compte.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class CompteSeederConfig {

    @Bean
    CommandLineRunner compteSeeder(CompteRepository compteRepository) {
        return args -> {
            if (compteRepository.count() == 0) {
                List<Compte> comptes = List.of(
                        new Compte(null, "+221774476276", 1234567890125L, BigDecimal.valueOf(1500)),
                        new Compte(null, "+221774730039", 1234567890123L, BigDecimal.valueOf(1000)),
                        new Compte(null, "+221771234567", 1234567890124L, BigDecimal.valueOf(500))
                );
                compteRepository.saveAll(comptes);
            }
        };
    }
}