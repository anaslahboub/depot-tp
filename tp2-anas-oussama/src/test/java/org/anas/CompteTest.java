package org.anas;

import org.anas.tp.*;
import org.anas.tp.CompteDecouvert;
import org.anas.tp.CompteNormal;
import org.anas.tp.EtatTransitionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompteTest {
    private Compte compte;
    private EtatTransitionManager manager;
    @BeforeEach
    public void setUp() {
// Configuration du manager des transitions
        manager = EtatTransitionManager.Builder()
                .addTransition(CompteNormal.class, CompteDecouvert.class, c -> c.getSolde() < 0)
                .addTransition(CompteDecouvert.class, CompteBloque.class, c -> c.getSolde() < -500)
                .addTransition(CompteDecouvert.class, CompteNormal.class, c -> c.getSolde() >= 0)

                .addTransition(CompteBloque.class, CompteNormal.class, c -> c.getSolde() >= 0)
                .build();
        compte = new Compte(manager);

    }
    @Test
    public void testDepotSimple() {
        compte.deposer(1000);
        assertEquals(1000, compte.getSolde());
        assertEquals("CompteNormal", compte.getEtatName());
    }
    @Test
    public void testPassageNormalADecouvert() {
        compte.retirer(100);
        assertEquals(-100, compte.getSolde());
        assertEquals("CompteDecouvert", compte.getEtatName());
    }
    @Test
    public void testPassageDecouvertABloque() {
        compte.retirer(100); // solde devient -100
        compte.retirer(500); // solde devient -600
        assertEquals(-600, compte.getSolde());
        assertEquals("CompteBloque", compte.getEtatName());
    }
    @Test
    public void testRetourDecouvertANormal() {
        compte.retirer(100); // solde = -100 -> CompteDecouvert
        assertEquals("CompteDecouvert", compte.getEtatName());
        compte.deposer(200); // solde = 100 -> CompteNormal
        assertEquals(100, compte.getSolde());
        assertEquals("CompteNormal", compte.getEtatName());

    }
    @Test
    public void testRetourBloqueANormal() {
        compte.retirer(100); // solde devient -100
        compte.retirer(500); // solde devient -600
        assertEquals("CompteBloque", compte.getEtatName());
        compte.deposer(700); // solde = 100 -> CompteNormal
        assertEquals(100, compte.getSolde());
        assertEquals("CompteNormal", compte.getEtatName());
    }
    @Test
    public void testRetraitBloque() {
        compte.retirer(100); // solde devient -100
        compte.retirer(500); // solde devient -600
        double soldeAvant = compte.getSolde();
        compte.retirer(100); // retrait refusé
        assertEquals(soldeAvant, compte.getSolde());
        assertEquals("CompteBloque", compte.getEtatName());
    }
}