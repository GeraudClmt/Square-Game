package fr.squaregame.components;

public class ArtificialPlayer extends Player{
    public ArtificialPlayer(String representation) {
        super(representation);
        this.type = Type.MACHINE;
    }
}
