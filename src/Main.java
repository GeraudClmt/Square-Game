import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;
import fr.squaregame.controller.games.Connect4;
import fr.squaregame.controller.games.Gomoku;
import fr.squaregame.controller.games.TicTacToe;

import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
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