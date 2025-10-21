package fr.squaregame.games.tictactoe;

import fr.squaregame.components.*;
import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;
import fr.squaregame.games.Game;

public class TicTacToe extends Game {
    public TicTacToe(){
        super.width = 3;
        super.height = 3;
        super.signList = new String[]{"\u001B[91mX\u001B[0m", "\u001B[94mO\u001B[0m"};
        super.board = new Board(height, width, " ");
        super.winningLength = 3;
        super.countTurnPlayed = 0;
    }

    public void playerTurn(Player player, InteractionUtilisateur interactUser, View view)throws PlayerWin, BoardIsFull{
        int[] coordinate = new int[]{};
        while(coordinate.length < 2){
            try{
                coordinate = player.getCoordinates(interactUser, view, board);
            } catch (ArrayIndexOutOfBoundsException e) {
                view.printMessage("Coordonnées en dehors du plateau\n");
            }
        }

        setOwner(coordinate[0], coordinate[1], player);
        view.display(board);

        isOver(coordinate[0], coordinate[1]);
    }
}