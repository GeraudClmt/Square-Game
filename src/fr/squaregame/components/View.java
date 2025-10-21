package fr.squaregame.components;

public class View {
    public void printMessage(String message){
        System.out.println(message);
    }

    public void display(Board board){
        printMessage(board.toString());
    }
}
