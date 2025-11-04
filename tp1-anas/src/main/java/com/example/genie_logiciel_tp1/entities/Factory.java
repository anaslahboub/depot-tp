package com.example.genie_logiciel_tp1.entities;

import com.example.genie_logiciel_tp1.IReportable;

import java.util.ArrayList;
import java.util.List;

public class Factory implements IReportable {
    private String pays;
    private List<Entrepot> entrepots;
    private String transitStock;



    public double moveStock(double i){
        if(entrepots==null){
            entrepots = new ArrayList<>();
        }

        double total=0;
        for(Entrepot entrepot: entrepots){
              total+=entrepot.moveStock(i);
        }

        return total;
    }

    public int currentProductionCapacity() {
        int total = 0;
        for (Entrepot  entrepot: this.entrepots) {
            total+=Integer.parseInt(entrepot.getStock());
        }

        return total;
    }

    public String report(){
        StringBuilder  builder = new StringBuilder();
        int i=1;
        builder.append(this.pays.toUpperCase().charAt(0)).append(":<");
        for (Entrepot  entrepot: this.entrepots) {
            builder.append(entrepot.report(pays,i++));
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append(">");
        if (transitStock != null) {
            builder.append("-").append(this.transitStock);
        }
        return builder.toString();
    }





    public void setTransitStock(String transitStock) {
        this.transitStock = transitStock;
    }
    public String getTransitStock() {
        return transitStock;
    }
    public String getPays() {
        return pays;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }
    public List<Entrepot> getEntrepots() {
        return entrepots;
    }
    public void setEntrepots(List<Entrepot> entrepots) {
        this.entrepots = entrepots;
    }


}
