import fr.squaregame.components.Cell;
import fr.squaregame.components.Player;
import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;
import fr.squaregame.tictactoe.TicTacToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    @Test
    public void testBoard() {
        TicTacToe game = new TicTacToe();
        assertEquals("\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------", game.display());
    }

    @Test
    public void testBoardIsFull() {
        Player player1 = new Player("X");
        Player player2 = new Player("O");
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
            game.isOver(" X ");
        });
    }

    @Test
    public void testPlayerWinLine1() {
        Player player1 = new Player("X");
        Player player2 = new Player("O");
        TicTacToe game = new TicTacToe();

        game.setOwner(0,0, player1);
        game.setOwner(0,1, player1);
        game.setOwner(0,2, player1);

        assertThrows(PlayerWin.class, () -> {
            game.isOver(" X ");
        });
    }
    @Test
    public void testPlayerWinLine2() {
        Player player1 = new Player("X");
        Player player2 = new Player("O");
        TicTacToe game = new TicTacToe();

        game.setOwner(1,0, player1);
        game.setOwner(1,1, player1);
        game.setOwner(1,2, player1);

        assertThrows(PlayerWin.class, () -> {
            game.isOver(" X ");
        });
    }
    @Test
    public void testPlayerWinColum1() {
        Player player1 = new Player("X");
        Player player2 = new Player("O");
        TicTacToe game = new TicTacToe();

        game.setOwner(0,1, player1);
        game.setOwner(1,1, player1);
        game.setOwner(2,1, player1);

        assertThrows(PlayerWin.class, () -> {
            game.isOver(" X ");
        });
    }
}
