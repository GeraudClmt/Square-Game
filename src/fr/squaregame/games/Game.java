package fr.squaregame.games;

import fr.squaregame.components.Board;

public abstract class Game {
    protected Board board;
    protected int width;
    protected int height;

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
}