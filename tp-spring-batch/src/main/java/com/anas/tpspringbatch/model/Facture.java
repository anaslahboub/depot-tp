package com.anas.tpspringbatch.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double montantHT;

    @Column(nullable = false)
    private Double tauxTVA;

    @Column(nullable = false)
    private Double montantTTC;

    // Constructeur sans montantTTC (calculé par le processor)
    public Facture(Long id, Double montantHT, Double tauxTVA) {
        this.id = id;
        this.montantHT = montantHT;
        this.tauxTVA = tauxTVA;
    }
}