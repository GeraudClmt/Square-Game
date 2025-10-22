package fr.squaregame.controller;

import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;
import fr.squaregame.model.*;
import fr.squaregame.model.player.ArtificialPlayer;
import fr.squaregame.model.player.HumanPlayer;
import fr.squaregame.model.player.Player;
import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;

import java.util.Random;

public abstract class Game {
    protected Player player1;
    protected Player player2;
    protected Board board;
    protected int width;
    protected int height;
    protected int countTurnPlayed;
    protected int winningLength;
    protected String[] signList;

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

    public String getBoardToString(){
        return  board.toString();
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

    public void play(InteractionUtilisateur interactUser, View view){
        view.printMessage("Début du jeux");
        boolean isPlay = true;
        String signPlayer1;
        String signPlayer2;

        boolean isHumanPlayer1 = interactUser.isPositifResponse("Le premier joueur est un humain ? Y / N");
        if(isHumanPlayer1){
            signPlayer1 = interactUser.getSign(signList);
            player1 = new HumanPlayer(signPlayer1);
        }else {
            signPlayer1 = signList[0];
            player1 = new ArtificialPlayer(signPlayer1);
        }

        if(signPlayer1.equals(signList[0])){
            signPlayer2 = signList[1];
        }else{
            signPlayer2 = signList[0];
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
                view.printMessage(board.toString());
                view.printMessage("\nAu tour du joueur 1");
                playerTurn(player1, interactUser, view);
                view.printMessage("\nAu tour du joueur 2");
                playerTurn(player2, interactUser, view);

            } catch (PlayerWin e) {
                view.printMessage("Le joueur " + e.getMessage() + " gagne");
                isPlay = false;
            } catch (BoardIsFull e){
                view.printMessage(e.getMessage());
                isPlay =  false;
            }
        }
    }

    public int[] getCoordinates(InteractionUtilisateur interactUser, View view) throws ArrayIndexOutOfBoundsException {
        int line = interactUser.getInputInt("Entrez une ligne") - 1;
        int column = interactUser.getInputInt("Entrez une colonne") - 1;

        if(!board.getCell(line, column).getRepresentation().equals("   ")){
            view.printMessage("Case déja prise\n");
            return getCoordinates(interactUser, view);
        }else{
            return new int[]{line, column};
        }
    }

    public int[] getCoordinatesForArtificialPlayer(InteractionUtilisateur interactUser, View view) throws ArrayIndexOutOfBoundsException {
        Random rand = new Random();
        int row = rand.nextInt(0, board.getHeight());
        int col = rand.nextInt(0, board.getWidth());

        if (board.isEmptyCell(row, col)) {
            return new int[]{row, col};
        }

        return getCoordinatesForArtificialPlayer(interactUser, view);
    }

    public void playerTurn(Player player, InteractionUtilisateur interactUser, View view)throws PlayerWin, BoardIsFull{
        int[] coordinate = new int[]{};
        while(coordinate.length < 2){
            try{
                if(player instanceof ArtificialPlayer){
                    coordinate = getCoordinatesForArtificialPlayer(interactUser, view);
                }else{
                    coordinate = getCoordinates(interactUser, view);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                view.printMessage("Coordonnées en dehors du plateau\n");
            }
        }

        setOwner(coordinate[0], coordinate[1], player);
        view.printMessage(board.toString());

        isOver(coordinate[0], coordinate[1]);
    }
}