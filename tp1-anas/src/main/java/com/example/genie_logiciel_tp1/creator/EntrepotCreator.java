package com.example.genie_logiciel_tp1.creator;

import com.example.genie_logiciel_tp1.entities.Entrepot;

import java.util.ArrayList;
import java.util.List;

public class EntrepotCreator {


    public static List<Entrepot> getEntrepots(String... data){
        List<Entrepot> entrepots=new ArrayList<Entrepot>();
        for(int i=0;i<data.length-1;i=i+2){
            Entrepot entrepot=new Entrepot();
            entrepot.setStock(data[i]);
            entrepot.setNumberEmployee(data[i+1]);
            entrepots.add(entrepot);
        }

        return entrepots;
    }

}
