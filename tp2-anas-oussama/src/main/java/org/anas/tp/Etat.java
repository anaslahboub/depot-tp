package org.anas.tp;

public abstract class  Etat {

    private Compte compte;
    public Compte getCompte() {
        return compte;
    }
    public void setCompte(Compte compte) {
        this.compte = compte;
    }


    public abstract void depot(double solde);
    public abstract void retirer(double solde);
    public abstract String getEtatName();


}
