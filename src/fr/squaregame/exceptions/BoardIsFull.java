package fr.squaregame.exceptions;

public class BoardIsFull extends RuntimeException {
    public BoardIsFull(String message) {
        super(message);
    }
}
