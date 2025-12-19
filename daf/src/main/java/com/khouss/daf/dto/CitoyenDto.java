package com.khouss.daf.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitoyenDto {

    @NotNull
    private Long numCni;

    @NotBlank
    private String prenom;

    @NotBlank
    private String nom;

    private String adresse;

    @NotNull
    private LocalDate ddn;

}
