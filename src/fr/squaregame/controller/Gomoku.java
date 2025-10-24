package fr.squaregame.controller;


/**
 * Implémentation du jeu Gomoku (aligner 5 pions) sur une grille 15x15.
 */
public class Gomoku extends Game {
    /**
     * Constructeur de Gomoku
     */
    public Gomoku(){
        super(15, 15, new String[]{"\u001B[91mX\u001B[0m", "\u001B[94mO\u001B[0m"}, 5);
    }

}