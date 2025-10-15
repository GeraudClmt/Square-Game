import fr.squaregame.components.InputOutput;
import fr.squaregame.components.Player;
import fr.squaregame.tictactoe.TicTacToe;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InputOutput inputOutput = new InputOutput();

        TicTacToe gameTicTactToe = new TicTacToe();
        gameTicTactToe.play(inputOutput);
    }
}