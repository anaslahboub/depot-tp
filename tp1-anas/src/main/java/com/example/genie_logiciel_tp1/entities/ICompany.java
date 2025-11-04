package com.example.genie_logiciel_tp1.entities;

import java.util.List;

public class ICompany {

    public static Company getPlusCapacity(List<Company> companies) {
        int max = 0;
        Company result = null;
        for (Company company : companies) {
            int capacity = company.currentProductionCapacity();
            if (max < capacity) {
                max = capacity;
                result = company;
            }
        }
        return result;
    }


    public static String getPlusProchFactory(Company targetCompany, List<String> logisticsNetwork) {
        int minDistance = Integer.MAX_VALUE;
        String closestCountry = null;

        for (Factory factory : targetCompany.getFactories()) {
            String targetCountry = factory.getPays();

            for (String route : logisticsNetwork) {
                String[] parts = route.split(":");
                    String from = parts[0];
                    String to = parts[2];
                    int distance = Integer.parseInt(parts[1]);

                    if (to.equals(targetCountry) && distance < minDistance) {
                        minDistance = distance;
                        closestCountry = from;
                    }
                    if (from.equals(targetCountry) && distance < minDistance) {
                        minDistance = distance;
                        closestCountry = to;
                    }

            }
        }
        return closestCountry;
    }
}