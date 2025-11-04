package org.anas.tp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EtatTransitionManagerBuilder {

    List<Transition> transitions;

    public EtatTransitionManagerBuilder addTransition(Class<? extends Etat> from, Class<? extends Etat> to, Predicate<Compte> predicate) {
        if (this.transitions == null) {
            this.transitions = new ArrayList<>();
        }
         transitions.add( new Transition(from,to,predicate));
        return this;

    }

    public EtatTransitionManager build() {
        return new EtatTransitionManager(this.transitions);
    }

}
