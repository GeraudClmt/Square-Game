import fr.squaregame.components.Board;
import fr.squaregame.components.HumanPlayer;
import fr.squaregame.components.Player;
import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;
import fr.squaregame.games.tictactoe.TicTacToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    @Test
    public void testBoardIsFull() {
        Player player1 = new HumanPlayer("X");
        Player player2 = new HumanPlayer("O");
        TicTacToe game = new TicTacToe();

        game.setOwner(0,0, player1);
        game.setOwner(0,1, player1);
        game.setOwner(0,2, player2);

        game.setOwner(1,0, player2);
        game.setOwner(1,1, player2);
        game.setOwner(1,2, player1);

        game.setOwner(2,0, player1);
        game.setOwner(2,1, player2);
        game.setOwner(2,2, player1);

        assertThrows(BoardIsFull.class, () -> {
            game.isOver(2,2);
        });
    }

    @Test
    public void testPlayerWinLine1() {
        Player player1 = new HumanPlayer("X");
        Player player2 = new HumanPlayer("O");
        TicTacToe game = new TicTacToe();

        game.setOwner(0,0, player1);
        game.setOwner(0,1, player1);
        game.setOwner(0,2, player1);

        assertThrows(PlayerWin.class, () -> {
            game.isOver(0,2);
        });
    }
    @Test
    public void testPlayerWinLine2() {
        Player player1 = new HumanPlayer("X");
        Player player2 = new HumanPlayer("O");
        TicTacToe game = new TicTacToe();

        game.setOwner(1,0, player1);
        game.setOwner(1,1, player1);
        game.setOwner(1,2, player1);

        assertThrows(PlayerWin.class, () -> {
            game.isOver(1,2);
        });
    }
    @Test
    public void testPlayerWinColum1() {
        Player player1 = new HumanPlayer("X");
        Player player2 = new HumanPlayer("O");
        TicTacToe game = new TicTacToe();

        game.setOwner(0,1, player1);
        game.setOwner(1,1, player1);
        game.setOwner(2,1, player1);

        assertThrows(PlayerWin.class, () -> {
            game.isOver(2,1);
        });
    }
}
