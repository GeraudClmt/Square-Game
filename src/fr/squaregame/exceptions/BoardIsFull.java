package fr.squaregame.exceptions;

/**
 * Exception indiquant que le plateau est entièrement rempli sans vainqueur.
 */
public class BoardIsFull extends RuntimeException {
    /**
     * Crée une nouvelle exception avec un message explicatif.
     *
     * @param message description de l'erreur
     */
    public BoardIsFull(String message) {
        super(message);
    }
}
