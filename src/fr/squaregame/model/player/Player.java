package fr.squaregame.model.player;

/**
 * Représente un joueur (humain ou artificiel) avec une représentation de pion.
 */
public abstract class Player {
    protected final String representation;

    /**
     * Construit un joueur avec la représentation donnée.
     *
     * @param representation symbole ou chaîne pour afficher le pion du joueur
     */
    public Player(String representation){
        this.representation = representation;
    }

    /**
     * Retourne la représentation du joueur.
     * @return La représentation du joueur
     */
    public String getRepresentation(){
        return representation;
    }
}
