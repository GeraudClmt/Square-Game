package fr.squaregame.model.player;

import fr.squaregame.model.Board;
import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;

/**
 * Joueur contrôlé par un humain.
 */
public class HumanPlayer extends Player {
    /**
     * Crée un joueur humain avec la représentation donnée.
     *
     * @param representation symbole du pion
     */
    public HumanPlayer(String representation) {
        super(representation);
    }

}
