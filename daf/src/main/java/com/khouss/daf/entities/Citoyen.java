package com.khouss.daf.entities;
import jakarta.persistence.*;;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Citoyen {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private UUID id;

     @Column(unique = true,nullable = false)
     private Long numCni;
     private String Prenom;
     private String Nom;
     private String Adresse;
     private Date Ddn;

}
