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

    public String getSign(){
        String sign = "R";

        while(!sign.equals("X") && !sign.equals("O")){
            printMessage("Choisissez X ou O");
            sign =  scanner.next().toUpperCase();
            if(!sign.equals("X") && !sign.equals("O")){
                printMessage("Erreur de choix !");
            }
        }

        return sign;
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
