package com.example.genie_logiciel_tp1.builder;

import com.example.genie_logiciel_tp1.entities.Company;
import com.example.genie_logiciel_tp1.entities.SupplyChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SupplyChainBuilder {
    private List<Company> companies;
    private List<String>  logisticsNetwork;
    private int orderSize;


    public SupplyChainBuilder addCompany(Company company1) {
        if (this.companies == null) {
            this.companies = new ArrayList<>();
        }
        this.companies.add(company1);
        return this;
    }

    public SupplyChainBuilder addLogisticsNetwork(String routeString) {
        if (this.logisticsNetwork == null) {
            this.logisticsNetwork = new ArrayList<>();
        }

        String[] route = routeString.split(",");
        this.logisticsNetwork.addAll(Arrays.asList(route));

        return this;
    }
    public SupplyChainBuilder setOrderSize(int i) {
        orderSize = i;
        return this;
    }
    public SupplyChain  build() {
        SupplyChain supplyChain = new SupplyChain();
        supplyChain.setCompanies(companies);
        supplyChain.setLogisticsNetwork(logisticsNetwork);
        supplyChain.setOrderSize(orderSize);
        return supplyChain;
    }


}
