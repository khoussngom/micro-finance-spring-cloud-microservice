package com.khouss.compte.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comptes",
uniqueConstraints = {
                        @UniqueConstraint(name="num_comptes", columnNames = {"numeroTelephone"}),
                        @UniqueConstraint(name="cni",columnNames = {"numCni"})
                    },
indexes = {
                        @Index(name="idx_num_comptes", columnList = "numeroTelephone"),
                        @Index(name="idx_cni", columnList = "numCni")
         }
)
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String numeroTelephone;
    @Column(nullable = false)
    private Long numCni;
    @Column(nullable = false)
    private Double solde = 0.0;

}
