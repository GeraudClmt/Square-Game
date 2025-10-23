package fr.squaregame.view;

import java.util.List;
import java.util.Scanner;

/**
 * Gère les interactions utilisateurs via la console (saisie clavier et affichage).
 */
public class InteractionUtilisateur {
    /** Scanner de lecture de l'entrée standard. */
    private final Scanner scanner;
    /** Vue responsable de l'affichage. */
    private final View view;

    /**
     * Construit un gestionnaire d'interactions utilisateur.
     *
     * @param view composant d'affichage associé
     */
    public InteractionUtilisateur(View view) {
        this.scanner = new Scanner(System.in);
        this.view = view;
    }

    /**
     * Demande un entier à l'utilisateur, avec gestion des erreurs et relance en cas de saisie invalide.
     *
     * @param message message d'invite
     * @return l'entier saisi
     */
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

    /**
     * Demande à l'utilisateur de choisir un pion parmi une liste.
     *
     * @param signList liste des représentations de pions disponibles
     * @return la représentation choisie
     */
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

    /**
     * Pose une question fermée (Y/N) et renvoie la réponse sous forme booléenne.
     *
     * @param message question à afficher
     * @return true si réponse "Y", false si réponse "N"
     */
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

    /**
     * Affiche une liste de jeux et récupère un choix valide auprès de l'utilisateur.
     *
     * @param gameList liste des options de jeux à afficher
     * @return l'indice du jeu choisi (1..N)
     */
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
