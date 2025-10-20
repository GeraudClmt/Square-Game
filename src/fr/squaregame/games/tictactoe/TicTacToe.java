package fr.squaregame.games.tictactoe;

import fr.squaregame.components.*;
import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;
import fr.squaregame.games.Game;

public class TicTacToe extends Game {
    private Player player1;
    private Player player2;
    private int countTurnPlayed;
    private final int winningLength;

    public TicTacToe(){
        super.width = 3;
        super.height = 3;
        super.board = new Board(height, width);
        winningLength = 3;
        countTurnPlayed = 0;
    }

    public void setOwner(int x, int y, Player player){
        board.setCell(x, y, new Cell(player.getRepresentation()));
        countTurnPlayed++;
    }

    public void isOver(int row, int col)throws PlayerWin, BoardIsFull {
        if(countAlignement(row, col) >= winningLength){
            throw new PlayerWin(board.getCell(row, col).getRepresentation());
        }

        if(countTurnPlayed == width * height){
            System.out.println("C'est plein ");
            throw new BoardIsFull("Egalité, le plateau est complet");
        }
    }

    public void playerTurn(Player player, InteractionUtilisateur interactUser, View view)throws PlayerWin, BoardIsFull{
        int[] coordinate = new int[]{};
        while(coordinate.length < 2){
            try{
                coordinate = player.getCoordinates(interactUser, view, board);
            } catch (ArrayIndexOutOfBoundsException e) {
                view.printMessage("Coordonnées en dehors du plateau\n");
            }
        }

        setOwner(coordinate[0], coordinate[1], player);
        view.display(board);

        isOver(coordinate[0], coordinate[1]);
    }

    public void play(InteractionUtilisateur interactUser, View view){
        view.printMessage("Début du jeux");
        boolean isPlay = true;
        String signPlayer1;
        String signPlayer2;

        boolean isHumanPlayer1 = interactUser.isPositifResponse("Le premier joueur est un humain ? Y / N");
        if(isHumanPlayer1){
            signPlayer1 = interactUser.getSign();
            player1 = new HumanPlayer(signPlayer1);
        }else {
            signPlayer1 = "X";
            player1 = new ArtificialPlayer(signPlayer1);
        }

        if(signPlayer1.equals("X")){
            signPlayer2 = "O";
        }else{
            signPlayer2 = "X";
        }

        boolean isHumanPlayer2 = interactUser.isPositifResponse("Le deuxième joueur est un humain ? Y / N");
        if(isHumanPlayer2){
            player2 = new HumanPlayer(signPlayer2);
        }else{
            player2 = new ArtificialPlayer(signPlayer2);
        }

        view.printMessage("Joueur 1 : " + signPlayer1 + " et Joueur 2 : " + signPlayer2);

        while(isPlay){
            try{
                view.display(board);
                view.printMessage("\nAu tour du joueur 1");
                playerTurn(player1, interactUser, view);
                view.printMessage("\nAu tour du joueur 2");
                playerTurn(player2, interactUser, view);

            } catch (PlayerWin e) {
                if(player1.getRepresentation().equals(e.getMessage().trim())){
                    view.printMessage("Le joueur 1 gagne");
                }else{
                    view.printMessage("Le joueur 2 gagne");
                }
                isPlay = false;
            } catch (BoardIsFull e){
                view.printMessage(e.getMessage());
                isPlay =  false;
            }
        }
    }
}