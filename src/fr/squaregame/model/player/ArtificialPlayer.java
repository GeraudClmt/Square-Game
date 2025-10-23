package fr.squaregame.model.player;

import fr.squaregame.model.Board;
import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;

import java.util.Random;

/**
 * Joueur artificiel basique.
 */
public class ArtificialPlayer extends Player {
    /**
     * Crée un joueur artificiel avec la représentation donnée.
     *
     * @param representation symbole du pion
     */
    public ArtificialPlayer(String representation) {
        super(representation);
    }
}
