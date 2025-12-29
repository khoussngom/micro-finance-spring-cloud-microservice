package com.khouss.compte.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCompte {
    private String numeroTelephone;
    private Double solde;
    private Long numCni;
}
