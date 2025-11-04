package com.example.genie_logiciel_tp1.creator;

import com.example.genie_logiciel_tp1.entities.Entrepot;
import com.example.genie_logiciel_tp1.entities.Factory;

public class FactoryCreator {
    Factory f = new Factory();

    public static Factory getFactory(String pays,String... data) {
        Factory f = new Factory();
        f.setPays(pays);
        f.setEntrepots(EntrepotCreator.getEntrepots(data));
        return f;
    }

}
