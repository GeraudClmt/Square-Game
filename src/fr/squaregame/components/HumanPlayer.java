package fr.squaregame.components;

public class HumanPlayer extends Player{

    public HumanPlayer(String representation) {
        super(representation);
        this.type = Type.HUMAIN;
    }
}
