package com.anas.batch.config;

import com.anas.batch.models.Facture;
import com.anas.batch.models.FactureDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FactureProcessor implements ItemProcessor<FactureDto, Facture> {

    @Override
    public Facture process(FactureDto factureDto) {
        double montantTTC = factureDto.getMontantHT() * (1 + factureDto.getTauxTVA() / 100);
        Facture facture = new Facture();
        facture.setId(factureDto.getId());
        facture.setMontantHT(factureDto.getMontantHT());
        facture.setTauxTVA(factureDto.getTauxTVA());
        facture.setMontantTTC(montantTTC);

        return facture; // Retourne le DTO transformé
    }
}
