package com.example.genie_logiciel_tp1.entities;

import java.util.Comparator;
import java.util.List;

public class SupplyChain {

    private List<Company> companies;
    private List<String>  logisticsNetwork;
    private  int orderSize;

    public int  getOrderSize() {
        return orderSize;
    }
    public void setOrderSize(int orderSize) {
        this.orderSize = orderSize;
    }


    public void prepareOrder() {

        Company company1 = companies.stream()
                .max(Comparator.comparing(Company::currentProductionCapacity)).get();

        Company company2 =(company1.equals(companies.get(0)))?companies.get(1):companies.get(0);

        String payPlusProche = ICompany.getPlusProchFactory(company2, logisticsNetwork);

        for(Factory factory:company1.getFactories()){
            if (factory.getPays().equals(payPlusProche)) {
                double move = factory.moveStock(0.4);
                int transit = Integer.parseInt(factory.getTransitStock());
                transit +=move;

                factory.setTransitStock(String.valueOf(transit));
            }
        }
    }
    public void setLogisticsNetwork(List<String> logisticsNetwork) {
        this.logisticsNetwork = logisticsNetwork;
    }

    public List<String> getLogisticsNetwork() {
        return logisticsNetwork;
    }

    List<Company> getCompanies() {
        return companies;
    }
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }


    public void executeDelivery() {
//        Company company1 = ICompany.getPlusCapacity(companies);

        Company company1 = companies.stream().max(Comparator.comparing(Company::currentProductionCapacity)).get();
        Company company2 = (company1.equals(companies.get(0))) ? companies.get(1) : companies.get(0);

        String payPlusProche = ICompany.getPlusProchFactory(company2, logisticsNetwork);

        for (Factory factory : company1.getFactories()) {
            if (factory.getPays().equals(payPlusProche)) {
                int currentTransit = Integer.parseInt(factory.getTransitStock());
                int finalTransit = currentTransit - orderSize;
                String newTransit = Integer.toString(finalTransit);
                factory.setTransitStock(newTransit);
                break;
            }
        }

        for (Factory factory : company2.getFactories()) {
            int currentTransit = Integer.parseInt(factory.getTransitStock());
            int finalTransit = currentTransit + orderSize;
            String newTransit = Integer.toString(finalTransit);
            factory.setTransitStock(newTransit);
        }
    }
    public String deliveryStatus() {
        Company company1 = ICompany.getPlusCapacity(companies);
        Company company2 = (company1.equals(companies.get(0))) ? companies.get(1) : companies.get(0);

        // Calculer l'efficacité basée sur la capacité de production
        int capacity1 = company1.currentProductionCapacity();
        int capacity2 = company2.currentProductionCapacity();

        // Vérifier si la livraison a été efficace
        boolean efficientDelivery = true;

        // Vérifier que les stocks en transit sont suffisants
        for (Factory factory : company1.getFactories()) {
            int transitStock = Integer.parseInt(factory.getTransitStock());
            if (transitStock < 0) {
                efficientDelivery = false;
                break;
            }
        }

        // Vérifier que la capacité totale est suffisante pour la commande
        if (capacity1 < orderSize || !efficientDelivery) {
            return "INEFFICIENT";
        } else {
            return "EFFICIENT";
        }
    }
}
