package fr.squaregame.model.player;

import fr.squaregame.model.Board;
import fr.squaregame.model.Cell;

import java.util.ArrayList;
import java.util.List;

public class ArtificialPlayerTicTacToe {
    private final String representation;

    public ArtificialPlayerTicTacToe(){
        this.representation = "X";
    }
    public int[] getCoordinates(Board board, Player opponent){
        List<String> copyBoard = board.importBoar();
        Board newBoard = new Board(3,3, "   ");

        int compteur = 0;
        for(int i = 0; i < newBoard.getHeight(); i++){
            for(int j = 0; j < newBoard.getWidth(); j++){
                newBoard.setCell(i, j, new Cell(copyBoard.get(compteur)));
                compteur++;
            }
        }



        List<int[]> listOfAllPossibilityWithScore = getScoresForAllPossibility(newBoard);



        return new int[]{1,2};
    }

    public List<int[]> getScoresForAllPossibility(Board newBoard){
        List<int[]> scoresAndPosi = new ArrayList<>();
        List<int[]> positionListCellEmpty = newBoard.getListPositionCellsEmpty();

        positionListCellEmpty.forEach(position -> {

            //Faire condition full Board

            if(countAlignement(newBoard, position[0], position[1], representation) == 3){
                scoresAndPosi.add(new int[]{1, position[0], position[1]});
            }else if(countAlignement(newBoard, position[0], position[1], "O") == 3){
                scoresAndPosi.add(new int[]{0, position[0], position[1]});
            }else{
                scoresAndPosi.add(new int[]{-2, position[0], position[1]});
                Board newBoardAfterPlay = getNewBoardAfterPlay(newBoard, position[0], position[1]);
                //Rappel de la méthode
            }
        });

        return scoresAndPosi;
    }

    public Board getNewBoardAfterPlay(Board oldBoard, int newX, int newY){
        Board newBoard = new Board(3,3, "   ");
        newBoard.setCell(newX, newY, new Cell(representation));

        return newBoard;
    }


    public int countAlignement(Board board, int row, int col, String playerRepresentation){
        int maxOccurence = 0;
        int countOccurence = 0;
        int currentRow = row;
        int currentCol = col;

        //Horizontale
        while(currentCol < 3 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerRepresentation)){
            countOccurence++;
            currentCol++;
        }
        currentCol = col -1;
        while(currentCol >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerRepresentation)){
            countOccurence++;
            currentCol--;
        }

        maxOccurence = Math.max(maxOccurence, countOccurence);


        //Vertical
        countOccurence = 0;
        currentCol = col;
        while(currentRow < 3 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerRepresentation)){
            countOccurence++;
            currentRow++;
        }
        currentRow = row -1;
        while(currentRow >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerRepresentation)){
            countOccurence++;
            currentRow--;
        }
        maxOccurence = Math.max(maxOccurence, countOccurence);

        //Diagonal
        countOccurence = 0;
        currentRow = row;
        while(currentRow < 3 && currentCol < 3 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerRepresentation)){
            countOccurence++;
            currentRow++;
            currentCol++;
        }
        currentRow = row -1;
        currentCol = col -1;
        while(currentRow >= 0 && currentCol >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerRepresentation)){
            countOccurence++;
            currentRow--;
            currentCol--;
        }
        maxOccurence = Math.max(maxOccurence, countOccurence);

        //Diagonal
        countOccurence = 0;
        currentRow = row;
        currentCol = col;
        while(currentRow >= 0 && currentCol < 3 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerRepresentation)){
            countOccurence++;
            currentRow--;
            currentCol++;
        }
        currentRow = row +1;
        currentCol = col -1;
        while(currentRow < 3 && currentCol >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerRepresentation)){
            countOccurence++;
            currentRow++;
            currentCol--;
        }
        maxOccurence = Math.max(maxOccurence, countOccurence);


        return maxOccurence;
    }
}
