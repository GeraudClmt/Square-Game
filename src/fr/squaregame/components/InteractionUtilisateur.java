package fr.squaregame.components;

import java.util.Scanner;

public class InteractionUtilisateur {
    private final Scanner scanner;
    private final View view;

    public InteractionUtilisateur(View view){
        this.scanner = new Scanner(System.in);
        this.view = view;
    }

    public int getInputInt(String message){
        try{
            view.printMessage(message);
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            view.printMessage("Erreur ce n'est pas un entier");
            return getInputInt(message);
        }
    }

    public String getSign(){
        String sign = "R";

        while(!sign.equals("X") && !sign.equals("O")){
            view.printMessage("Choisissez X ou O");
            sign =  scanner.next().toUpperCase();
            if(!sign.equals("X") && !sign.equals("O")){
                view.printMessage("Erreur de choix !");
            }
        }

        return sign;
    }


    public boolean isPositifResponse(String message){
        view.printMessage(message);
         String input = scanner.next().toUpperCase();
         if(input.equals("Y")){
             return true;
         } else if (input.equals("N")) {
             return false;
         }
         else {
             view.printMessage("Erreur !!!");
             return isPositifResponse(message);
         }
    }
}
