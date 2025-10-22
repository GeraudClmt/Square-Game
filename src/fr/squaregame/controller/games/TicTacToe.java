package fr.squaregame.controller.games;


import fr.squaregame.controller.Game;
import fr.squaregame.model.Board;

public class TicTacToe extends Game {
    public TicTacToe(){
        super.width = 3;
        super.height = 3;
        super.signList = new String[]{"\u001B[91mX\u001B[0m", "\u001B[94mO\u001B[0m"};
        super.board = new Board(height, width, " ");
        super.winningLength = 3;
        super.countTurnPlayed = 0;
    }
}