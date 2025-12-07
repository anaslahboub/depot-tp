package com.anas.batch.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Facture {
    @Id
    private Long id;
    private double montantHT;
    private double tauxTVA;
    private double montantTTC;
    // Getters/Setters
}
