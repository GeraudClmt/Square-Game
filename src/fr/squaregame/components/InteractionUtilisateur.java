package fr.squaregame.components;

import java.util.List;
import java.util.Scanner;

public class InteractionUtilisateur {
    private final Scanner scanner;
    private final View view;

    public InteractionUtilisateur(View view) {
        this.scanner = new Scanner(System.in);
        this.view = view;
    }

    public int getInputInt(String message) {
        try {
            view.printMessage(message);
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            view.printMessage("Erreur ce n'est pas un entier");
            return getInputInt(message);
        }
    }

    public String getSign(String[] signList) {
        view.printMessage("Choisissez un pion");
        for(int i = 1; i < signList.length+1; i ++){
            view.printMessage(i + " - " + signList[i -1]);
        }

        try{
            int signNumber = scanner.nextInt();
            if (signNumber >= 1 && signNumber <= signList.length) {
                scanner.nextLine();
                return signList[signNumber-1];
            }
            view.printMessage("Erreur de choix");
            return getSign(signList);
        } catch (Exception e) {
            scanner.nextLine();
            view.printMessage("Ce n'est pas un entier !");
            return getSign(signList);
        }
    }

    public boolean isPositifResponse(String message) {
        view.printMessage(message);

        String input = scanner.nextLine().toUpperCase();
        if (input.equals("Y")) {
            return true;
        } else if (input.equals("N")) {
            return false;
        } else {
            view.printMessage("Erreur !!!");
            return isPositifResponse(message);
        }
    }

    public int getGameChoice(List<String> gameList) {
        for (String message : gameList) {
            view.printMessage(message);
        }
        try {
            int choice = scanner.nextInt();
            if (choice >= 1 && choice < gameList.toArray().length + 1) {
                scanner.nextLine();
                return choice;
            }
            view.printMessage("Erreur le choix n'est pas valide");
            return getGameChoice(gameList);
        } catch (Exception e) {
            scanner.nextLine();
            view.printMessage("Erreur ce n'est pas un entier");
            return getGameChoice(gameList);
        }
    }

}
