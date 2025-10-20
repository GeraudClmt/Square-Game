package fr.squaregame.games.connect4;

import fr.squaregame.components.*;
import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;
import fr.squaregame.games.Game;

public class Connect4 extends Game {

    public Connect4(){
        super.width = 7;
        super.height = 6;
        super.board = new Board(height, width, " ");
        super.winningLength = 4;
        super.countTurnPlayed = 0;
    }

    public void playerTurn(Player player, InteractionUtilisateur interactUser, View view)throws PlayerWin, BoardIsFull{
        int col = -1;
        int row = -1;
        while(col == -1){
            try{
                col = player.getCol(interactUser, view, board);
            } catch (ArrayIndexOutOfBoundsException e) {
                view.printMessage("Coordonnées en dehors du plateau\n");
            }
        }

        for(int i = height-1; i >= 0; i--){
            if(board.getCell(i, col).getRepresentation().equals("   ")){
                row = i;
                break;
            }
        }

        setOwner(row, col, player);
        view.display(board);
        isOver(row, col);
    }
}