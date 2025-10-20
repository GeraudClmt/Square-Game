import fr.squaregame.components.InteractionUtilisateur;
import fr.squaregame.components.View;
import fr.squaregame.games.connect4.Connect4;
import fr.squaregame.games.gomoku.Gomoku;
import fr.squaregame.games.tictactoe.TicTacToe;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        View view = new View();
        InteractionUtilisateur interactUser = new InteractionUtilisateur(view);

        TicTacToe gameTicTactToe = new TicTacToe();
        gameTicTactToe.play(interactUser, view);

        Gomoku gomokuGame = new Gomoku();
        gomokuGame.play(interactUser, view);

        Connect4 connect4Game = new Connect4();
        connect4Game.play(interactUser, view);
    }
}