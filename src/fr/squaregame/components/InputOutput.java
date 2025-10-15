package fr.squaregame.components;

import java.util.Scanner;

public class InputOutput {
    private final Scanner scanner;

    public InputOutput(){
        this.scanner = new Scanner(System.in);
    }

    public int getInputInt(String message){
        printMessage(message);
        return scanner.nextInt();
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
