package fr.squaregame.exceptions;

public class PlayerWin extends RuntimeException {
    public PlayerWin(String message) {
        super(message);
    }
}
