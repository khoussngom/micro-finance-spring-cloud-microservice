package com.khouss.compte.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
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
    private BigDecimal solde = BigDecimal.ZERO;

    public Compte() {}

    public Compte(String id, String numeroTelephone, Long numCni, BigDecimal solde) {
        this.id = id;
        this.numeroTelephone = numeroTelephone;
        this.numCni = numCni;
        this.solde = solde;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNumeroTelephone() { return numeroTelephone; }
    public void setNumeroTelephone(String numeroTelephone) { this.numeroTelephone = numeroTelephone; }

    public Long getNumCni() { return numCni; }
    public void setNumCni(Long numCni) { this.numCni = numCni; }

    public BigDecimal getSolde() { return solde; }
    public void setSolde(BigDecimal solde) { this.solde = solde; }

}
