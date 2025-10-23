package fr.squaregame.exceptions;

/**
 * Exception signalant qu'un joueur a remporté la partie.
 * Le message contient généralement la représentation du joueur vainqueur.
 */
public class PlayerWin extends RuntimeException {
    /**
     * Crée une exception de victoire de joueur.
     *
     * @param message représentation du joueur gagnant ou message explicatif
     */
    public PlayerWin(String message) {
        super(message);
    }
}
