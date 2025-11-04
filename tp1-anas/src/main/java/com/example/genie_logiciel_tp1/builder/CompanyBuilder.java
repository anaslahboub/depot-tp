package com.example.genie_logiciel_tp1.builder;

import com.example.genie_logiciel_tp1.creator.FactoryCreator;
import com.example.genie_logiciel_tp1.entities.Company;
import com.example.genie_logiciel_tp1.entities.Entrepot;
import com.example.genie_logiciel_tp1.entities.Factory;

import java.util.ArrayList;
import java.util.List;

public class CompanyBuilder {
    private String ceo;
    private List<Factory> factories;

    public CompanyBuilder setCEO(String CEO) {
        this.ceo = CEO;
        return this;
    }
    public CompanyBuilder addFactory(String pays,String... data) {
        if (factories == null) {
            factories = new ArrayList<Factory>();
        }
        Factory factory= FactoryCreator.getFactory(pays,data);
        this.factories.add(factory);
      return this;

    }

    public CompanyBuilder addTransitStock(String transitStock) {
        if (factories == null) {
            return this;
        }
        for (Factory factory : this.factories) {
            factory.setTransitStock(transitStock);
        }
        return this;
    }



    public Company build(){
        Company company = new Company();
        company.setCeo(this.ceo);
        company.setFactories(this.factories);
        return company;
    }



}
