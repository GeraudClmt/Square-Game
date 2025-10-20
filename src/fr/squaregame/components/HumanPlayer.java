package fr.squaregame.components;

public class HumanPlayer extends Player{

    public HumanPlayer(String representation) {
        super(representation);
    }

    @Override
    public int[] getCoordinates(InteractionUtilisateur interactUser, View view, Board board) throws ArrayIndexOutOfBoundsException {
        int line = interactUser.getInputInt("Entrez une ligne") - 1;
        int column = interactUser.getInputInt("Entrez une colonne") - 1;

        if(!board.getCell(line, column).getRepresentation().equals("   ")){
            view.printMessage("Case déja prise\n");
            return getCoordinates(interactUser, view, board);
        }else{
            return new int[]{line, column};
        }
    }

    @Override
    public int getCol(InteractionUtilisateur interactUser, View view, Board board) throws ArrayIndexOutOfBoundsException {
        int column = interactUser.getInputInt("Entrez une colonne") - 1;

        if(!board.getCell(0, column).getRepresentation().equals("   ")){
            view.printMessage("Colonne pleine\n");
            return getCol(interactUser, view, board);
        }else{
            return column;
        }
    }
}
