package fr.squaregame.controller;

import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;

import java.util.List;

/**
 * Point d'entrée de l'application console permettant de jouer à différents jeux de grille
 * (Morpion/TicTacToe, Gomoku et Puissance 4).
 */
public class SquareGame {

    /**
     * Choix du jeu et lancement de la game
     */
    public void start(){
        View view = new View();
        InteractionUtilisateur interactUser = new InteractionUtilisateur(view);
        List<String> gameList = List.of("1 - TictacToe", "2 - Gomoku", "3 - Puissance 4");

        view.printMessage("Choisissez un jeu : ");
        int choice = interactUser.getGameChoice(gameList);

        switch (choice) {
            case 1 :
                TicTacToe gameTicTactToe = new TicTacToe();
                gameTicTactToe.play(interactUser, view);
                break;
            case 2:
                Gomoku gomokuGame = new Gomoku();
                gomokuGame.play(interactUser, view);
                break;
            case 3:
                Connect4 connect4Game = new Connect4();
                connect4Game.play(interactUser, view);
                break;
            default:
                view.printMessage("A bientôt");
        }
    }
}
