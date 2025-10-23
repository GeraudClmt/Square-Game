import fr.squaregame.model.player.HumanPlayer;
import fr.squaregame.model.player.Player;
import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;
import fr.squaregame.controller.TicTacToe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    @Nested
    @DisplayName("Tests des conditions de victoires")
    class ConditionsDeVictoires{
        @Test
        @DisplayName("Test si le board est plein")
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
        @DisplayName("Test si joueur gagne sur la premiere ligne")
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
        @DisplayName("Test si joueur gagne sur la deuxième ligne")
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
        @DisplayName("Test si joueur gagne sur la premiere colonne")
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

    @Nested
    @DisplayName("Tests des positions du board de TictacToe")
    class TestsPositionsDuBoardTicTacToe{
        @Test
        @DisplayName("Test position 0 0")
        public void testPosition00(){
            Player player = new HumanPlayer("X");
            TicTacToe tictactoe = new TicTacToe();
            tictactoe.setOwner(0, 0, player);

            assertEquals("\n-------------\n| X |   |   |\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------", tictactoe.getBoardToString());
        }
        @Test
        @DisplayName("Test position 0 1")
        public void testPosition01(){
            Player player = new HumanPlayer("X");
            TicTacToe tictactoe = new TicTacToe();
            tictactoe.setOwner(0, 1, player);

            assertEquals("\n-------------\n|   | X |   |\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------", tictactoe.getBoardToString());
        }
        @Test
        @DisplayName("Test position 0 2")
        public void testPosition02(){
            Player player = new HumanPlayer("X");
            TicTacToe tictactoe = new TicTacToe();
            tictactoe.setOwner(0, 2, player);

            assertEquals("\n-------------\n|   |   | X |\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------", tictactoe.getBoardToString());
        }
        @Test
        @DisplayName("Test position 1 0")
        public void testPosition10(){
            Player player = new HumanPlayer("X");
            TicTacToe tictactoe = new TicTacToe();
            tictactoe.setOwner(1, 0, player);

            assertEquals("\n-------------\n|   |   |   |\n-------------\n| X |   |   |\n-------------\n|   |   |   |\n-------------", tictactoe.getBoardToString());
        }
        @Test
        @DisplayName("Test position 1 1")
        public void testPosition11(){
            Player player = new HumanPlayer("X");
            TicTacToe tictactoe = new TicTacToe();
            tictactoe.setOwner(1, 1, player);

            assertEquals("\n-------------\n|   |   |   |\n-------------\n|   | X |   |\n-------------\n|   |   |   |\n-------------", tictactoe.getBoardToString());
        }
        @Test
        @DisplayName("Test position 1 2")
        public void testPosition12(){
            Player player = new HumanPlayer("X");
            TicTacToe tictactoe = new TicTacToe();
            tictactoe.setOwner(1, 2, player);

            assertEquals("\n-------------\n|   |   |   |\n-------------\n|   |   | X |\n-------------\n|   |   |   |\n-------------", tictactoe.getBoardToString());
        }
        @Test
        @DisplayName("Test position 2 0")
        public void testPosition20(){
            Player player = new HumanPlayer("X");
            TicTacToe tictactoe = new TicTacToe();
            tictactoe.setOwner(2, 0, player);

            assertEquals("\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------\n| X |   |   |\n-------------", tictactoe.getBoardToString());
        }
        @Test
        @DisplayName("Test position 2 1")
        public void testPosition21(){
            Player player = new HumanPlayer("X");
            TicTacToe tictactoe = new TicTacToe();
            tictactoe.setOwner(2, 1, player);

            assertEquals("\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------\n|   | X |   |\n-------------", tictactoe.getBoardToString());
        }
        @Test
        @DisplayName("Test position 2 2")
        public void testPosition22(){
            Player player = new HumanPlayer("X");
            TicTacToe tictactoe = new TicTacToe();
            tictactoe.setOwner(2, 2, player);

            assertEquals("\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------\n|   |   | X |\n-------------", tictactoe.getBoardToString());
        }
    }
}
