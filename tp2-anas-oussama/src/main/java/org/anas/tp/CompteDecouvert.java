package org.anas.tp;

public class CompteDecouvert extends Etat  {
    @Override
    public void depot(double solde) {
        double actualDepot= super.getCompte().getSolde();
        super.getCompte().setSolde( actualDepot+solde);
    }

    @Override
    public void retirer(double solde) {
        double actualDepot= super.getCompte().getSolde();
        super.getCompte().setSolde( actualDepot-solde);
    }

    @Override
    public String getEtatName() {
        return "CompteDecouvert";
    }
}
