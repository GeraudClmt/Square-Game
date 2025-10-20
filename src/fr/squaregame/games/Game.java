package fr.squaregame.games;

import fr.squaregame.components.*;
import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;

public abstract class Game {
    protected Player player1;
    protected Player player2;
    protected Board board;
    protected int width;
    protected int height;
    protected int countTurnPlayed;
    protected int winningLength;

    public void setOwner(int x, int y, Player player){
        board.setCell(x, y, new Cell(player.getRepresentation()));
        countTurnPlayed++;
    }

    public void isOver(int row, int col)throws PlayerWin, BoardIsFull {
        if(countAlignement(row, col) >= winningLength){
            throw new PlayerWin(board.getCell(row, col).getRepresentation());
        }

        if(countTurnPlayed == width * height){
            throw new BoardIsFull("Egalité, le plateau est complet");
        }
    }

    public int countAlignement(int row, int col ){
        int maxOccurence = 0;
        int countOccurence = 0;
        int currentRow = row;
        int currentCol = col;
        String playerSign = board.getCell(row, col).getRepresentation();

        //Horizontale
        while(currentCol < width && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)){
            countOccurence++;
            currentCol++;
        }
        currentCol = col -1;
        while(currentCol >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)){
            countOccurence++;
            currentCol--;
        }

        maxOccurence = Math.max(maxOccurence, countOccurence);


        //Vertical
        countOccurence = 0;
        currentCol = col;
        while(currentRow < height && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)){
            countOccurence++;
            currentRow++;
        }
        currentRow = row -1;
        while(currentRow >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)){
            countOccurence++;
            currentRow--;
        }
        maxOccurence = Math.max(maxOccurence, countOccurence);

        //Diagonal
        countOccurence = 0;
        currentRow = row;
        while(currentRow < height && currentCol < width && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)){
            countOccurence++;
            currentRow++;
            currentCol++;
        }
        currentRow = row -1;
        currentCol = col -1;
        while(currentRow >= 0 && currentCol >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)){
            countOccurence++;
            currentRow--;
            currentCol--;
        }
        maxOccurence = Math.max(maxOccurence, countOccurence);

        //Diagonal
        countOccurence = 0;
        currentRow = row;
        currentCol = col;
        while(currentRow >= 0 && currentCol < width && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)){
            countOccurence++;
            currentRow--;
            currentCol++;
        }
        currentRow = row +1;
        currentCol = col -1;
        while(currentRow < height && currentCol >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)){
            countOccurence++;
            currentRow++;
            currentCol--;
        }
        maxOccurence = Math.max(maxOccurence, countOccurence);


        return maxOccurence;
    }

    public abstract void playerTurn(Player player, InteractionUtilisateur interactUser, View view)throws PlayerWin, BoardIsFull;

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