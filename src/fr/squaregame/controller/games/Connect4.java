package fr.squaregame.controller.games;

import fr.squaregame.controller.Game;
import fr.squaregame.model.Board;
import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;

import java.util.Random;

public class Connect4 extends Game {
    private final Random rand = new Random();

    public Connect4(){
        super.width = 7;
        super.height = 6;
        super.signList = new String[]{"\u001B[91m●\u001B[0m", "\u001B[93m●\u001B[0m"};
        super.board = new Board(height, width, " ");
        super.winningLength = 4;
        super.countTurnPlayed = 0;
    }

    @Override
    public int[] getCoordinates(InteractionUtilisateur interactUser, View view) throws ArrayIndexOutOfBoundsException {
        int column = interactUser.getInputInt("Entrez une colonne") - 1;

        if(!board.getCell(0, column).getRepresentation().equals("   ")){
            view.printMessage("Colonne pleine\n");
            return getCoordinates(interactUser, view);
        }else{
            return new int[]{getRow(column), column};
        }
    }

    @Override
    public int[] getCoordinatesForArtificialPlayer(InteractionUtilisateur interactUser, View view) throws ArrayIndexOutOfBoundsException {
        int col = rand.nextInt(0, board.getWidth());

        if (board.getCell(0, col).getRepresentation().equals("   ")) {
            return new int[]{getRow(col), col};
        }

        return getCoordinatesForArtificialPlayer(interactUser, view);
    }

    private int getRow(int col){
        for(int i = height-1; i >= 0; i--){
            if(board.getCell(i, col).getRepresentation().equals("   ")){
                return i;
            }
        }
        return -1;
    }
}