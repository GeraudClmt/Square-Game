package fr.squaregame.components;

public abstract class Player {
    protected final String representation;
    protected Type type;
    public Player(String representation){
        this.representation = representation;
    }

    public enum Type{
        HUMAIN,
        MACHINE
    }

    public String getRepresentation(){
        return representation;
    }

    public Type getType(){
        return this.type;
    }
}
