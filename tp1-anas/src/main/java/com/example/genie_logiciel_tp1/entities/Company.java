package com.example.genie_logiciel_tp1.entities;

import com.example.genie_logiciel_tp1.IReportable;

import java.util.List;

public class Company implements IReportable {
    private String ceo;
    private List<Factory> factories ;

    public int currentProductionCapacity() {
        int total = 0;
        for (Factory f : factories) {
            total+=f.currentProductionCapacity();
        }

        return total;

    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ceo.replace(" ","")).append(":|");
        for (Factory f : factories) {
            sb.append(f.report());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("|");
        return sb.toString();
    }











    public String getCeo() {
        return ceo;
    }
    public void setCeo(String ceo) {
        this.ceo = ceo;
    }
    public List<Factory> getFactories() {
        return factories;
    }
    public void setFactories(List<Factory> factories) {
        this.factories = factories;
    }


}

