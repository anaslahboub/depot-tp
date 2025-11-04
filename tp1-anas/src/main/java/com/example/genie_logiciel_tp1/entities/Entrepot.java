package com.example.genie_logiciel_tp1.entities;

import com.example.genie_logiciel_tp1.IReportable;

public class Entrepot implements IReportable {


    private String  stock;
    private String numberEmployee;


    @Override
    public String report() {
        return "";
    }
   public String report(String pay,int i) {
        StringBuilder  builder = new StringBuilder();
        builder.append(pay.toUpperCase().charAt(0)).append("e").append(i).append(":").append(stock).append("-").append(numberEmployee).append(",");
        return builder.toString();
   }

    public double moveStock(double i) {
        double currentStock = Integer.parseInt(this.stock);
        double finalStock = currentStock-currentStock*i;

        stock = Integer.toString((int)finalStock);
        return  currentStock * i;
    }


    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getNumberEmployee() {
        return numberEmployee;
    }

    public void setNumberEmployee(String numberEmployee) {
        this.numberEmployee = numberEmployee;
    }

}
