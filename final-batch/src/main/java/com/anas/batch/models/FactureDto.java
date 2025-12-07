package com.anas.batch.models;

import lombok.*;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class FactureDto {

    private Long id;
    private double montantHT;
    private double tauxTVA;
}
