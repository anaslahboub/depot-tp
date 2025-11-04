package org.anas.tp;

import java.util.function.Predicate;

public class Transition {

    private Class<? extends Etat> from ;
    private Class<? extends Etat> to ;
    Predicate<Compte> predicate;



    public Class<? extends Etat> getFrom() {
        return from;
    }

    public Class<? extends Etat> getTo() {
        return to;
    }

    public void setFrom(Class<? extends Etat> from) {
        this.from = from;
    }

    public void setTo(Class<? extends Etat> to) {
        this.to = to;
    }

    public Predicate<Compte> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<Compte> predicate) {
        this.predicate = predicate;
    }

    public Transition(  Class<? extends Etat> from , Class<? extends Etat> to,Predicate<Compte> predicate) {
        this.predicate = predicate;
        this.to = to;
        this.from = from;
    }
}
