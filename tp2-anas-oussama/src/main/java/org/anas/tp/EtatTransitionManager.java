package org.anas.tp;

import java.util.List;

public class EtatTransitionManager {

    public static  EtatTransitionManagerBuilder Builder() {
        return new EtatTransitionManagerBuilder();
    }

    public EtatTransitionManager(List<Transition> transitions) {
        this.transitions = transitions;
    }

    List<Transition> transitions;

    public void changeEtat(Compte compte)  {

        Etat acrtualEtat = compte.getEtat();
        Class<? extends Etat> from = acrtualEtat.getClass();

        for (Transition transition : transitions) {
            if (transition.getFrom().equals(from)) {
               if ( transition.getPredicate().test(compte)){
                   try {
                       Etat newEtat = transition.getTo().getDeclaredConstructor().newInstance();
                       newEtat.setCompte(compte);
                       compte.setEtat(newEtat);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
            }
        }


    }

}
