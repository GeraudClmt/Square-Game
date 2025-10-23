package fr.squaregame.controller;


import fr.squaregame.model.Board;

/**
 * Implémentation du jeu du Morpion (TicTacToe) sur une grille 3x3.
 */
public class TicTacToe extends Game {
    /**
     * Constructeur de Tictactoe
     */
    public TicTacToe(){
        super.width = 3;
        super.height = 3;
        super.signList = new String[]{"\u001B[91mX\u001B[0m", "\u001B[94mO\u001B[0m"};
        super.board = new Board(height, width, " ");
        super.winningLength = 3;
        super.countTurnPlayed = 0;
    }
}