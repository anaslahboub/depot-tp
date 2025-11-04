package org.anas.tp;

public class Compte {
    private double solde;
    private Etat etat=new CompteNormal();

     private EtatTransitionManager manager;

     public void deposer(double solde ){
         etat.setCompte(this);
        etat.depot(solde);
        manager.changeEtat(this);
     }

     public void retirer(double solde){
         etat.setCompte(this);
        etat.retirer(solde);
         manager.changeEtat(this);

     }

     public String getEtatName(){
        return etat.getEtatName();
     }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public EtatTransitionManager getManager() {
        return manager;
    }

    public void setManager(EtatTransitionManager manager) {
        this.manager = manager;
    }

    public Compte(EtatTransitionManager manager) {
        this.manager = manager;
    }
}
