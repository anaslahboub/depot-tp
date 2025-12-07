package com.anas.tpspringbatch.batch;

import com.anas.tpspringbatch.model.Facture;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FactureItemProcessor implements ItemProcessor<Facture, Facture> {

    @Override
    public Facture process(Facture facture) throws Exception {
        // Ignorer les factures avec montant négatif (Extension proposée)
        if (facture.getMontantHT() < 0) {
            log.warn("Facture {} ignorée : montant HT négatif ({})",
                    facture.getId(), facture.getMontantHT());
            return null; // null signifie que l'item est filtré
        }

        // Calculer le montant TTC
        Double montantTTC = facture.getMontantHT() * (1 + facture.getTauxTVA() / 100);
        facture.setMontantTTC(montantTTC);

        log.info("Facture traitée - ID: {}, HT: {}, TVA: {}%, TTC: {}",
                facture.getId(),
                facture.getMontantHT(),
                facture.getTauxTVA(),
                facture.getMontantTTC());

        return facture;
    }
}