package fr.squaregame.controller;

import fr.squaregame.model.Board;

/**
 * Implémentation du jeu Gomoku (aligner 5 pions) sur une grille 15x15.
 */
public class Gomoku extends Game {
    public Gomoku(){
        super.width = 15;
        super.height = 15;
        super.signList = new String[]{"\u001B[91mX\u001B[0m", "\u001B[94mO\u001B[0m"};
        super.board = new Board(height, width, " ");
        super.winningLength = 5;
        super.countTurnPlayed = 0;
    }

}