package com.khouss.daf.seeders;

import com.khouss.daf.dto.CitoyenDto;
import com.khouss.daf.entities.Citoyen;
import com.khouss.daf.repositories.CitoyenRepository;
import com.khouss.daf.services.CitoyenMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class CitoyenSeederConfig {

    @Bean
    CommandLineRunner citoyenSeeder(
            CitoyenRepository citoyenRepository,
            CitoyenMapper mapper
    ) {
        return args -> {
            if (citoyenRepository.count() == 0) {

                List<CitoyenDto> citoyens = List.of(
                        new CitoyenDto(1234567890123L, "Khouss", "Ngom", "Dakar", LocalDate.of(2000, 3, 21)),
                        new CitoyenDto(1234567890124L, "Moussa", "Ndiaye", "Diourbel", LocalDate.of(1985, 5, 15)),
                        new CitoyenDto(1234567890125L, "Fatou", "Wade", "Thies", LocalDate.of(1978, 9, 30))
                );

                List<Citoyen> entities = citoyens.stream()
                        .map(mapper::toEntity)
                        .toList();

                citoyenRepository.saveAll(entities);
            }
        };
    }
}
