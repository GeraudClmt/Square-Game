import fr.squaregame.components.Player;
import fr.squaregame.tictactoe.TicTacToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    @Test
    public void testBoard() {
        TicTacToe game = new TicTacToe(new Player("O"), new Player("X"));
        assertEquals("\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------", game.display());
    }
}
