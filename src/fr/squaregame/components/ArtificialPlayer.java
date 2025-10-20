package fr.squaregame.components;

import java.util.Random;

public class ArtificialPlayer extends Player {
    public ArtificialPlayer(String representation) {
        super(representation);
    }

    @Override
    public int[] getCoordinates(InteractionUtilisateur interactUser, View view, Board board) throws ArrayIndexOutOfBoundsException {
        Random rand = new Random();
        int row = rand.nextInt(0, board.getHeight());
        int col = rand.nextInt(0, board.getWidth());

        if (board.getCell(row, col).getRepresentation().equals("   ")) {
            return new int[]{row, col};
        }

        return getCoordinates(interactUser, view, board);
    }

    @Override
    public int getCol(InteractionUtilisateur interactUser, View view, Board board) throws ArrayIndexOutOfBoundsException {
        Random rand = new Random();
        int col = rand.nextInt(0, board.getWidth());

        if (board.getCell(0, col).getRepresentation().equals("   ")) {
            return col;
        }

        return getCol(interactUser, view, board);
    }
}
