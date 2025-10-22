package fr.squaregame.model.player;

import fr.squaregame.model.Board;
import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;

public abstract class Player {
    protected final String representation;
    public Player(String representation){
        this.representation = representation;
    }

    public String getRepresentation(){
        return representation;
    }
}
