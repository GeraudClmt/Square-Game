package fr.squaregame.controller;



/**
 * Implémentation du jeu du Morpion (TicTacToe) sur une grille 3x3.
 */
public class TicTacToe extends Game {
    /**
     * Constructeur de Tictactoe
     */
    public TicTacToe(){
        super(3, 3, new String[]{"\u001B[91mX\u001B[0m", "\u001B[94mO\u001B[0m"}, 3);
    }
}