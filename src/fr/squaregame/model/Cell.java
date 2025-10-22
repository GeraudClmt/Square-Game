package fr.squaregame.model;

public class Cell {
    private final String representation;

    public Cell(String representation){
        this.representation = " " + representation + " ";
    }
    public String getRepresentation(){
        return representation;
    }
    public boolean isEmpty(){
        return representation.equals("   ");
    }
}
