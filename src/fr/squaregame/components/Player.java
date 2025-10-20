package fr.squaregame.components;

public abstract class Player {
    protected final String representation;
    public Player(String representation){
        this.representation = representation;
    }

    public String getRepresentation(){
        return representation;
    }

    public abstract int[] getCoordinates(InteractionUtilisateur interactUser, View view, Board board) throws ArrayIndexOutOfBoundsException;

    public abstract int getCol(InteractionUtilisateur interactUser, View view, Board board) throws ArrayIndexOutOfBoundsException;
}
