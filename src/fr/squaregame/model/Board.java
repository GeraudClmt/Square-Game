package fr.squaregame.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Cell[][] cells;

    public Board(int height, int width, String emptyCell){
        cells = new Cell[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                cells[i][j] = new Cell(emptyCell);
            }
        }
    }

    @Override
    public String toString() {
        int width = this.getWidth();
        int height = this.getHeight();
        StringBuilder sortie = new StringBuilder();

        for(int i = 0; i < height; i++){
            sortie.append("\n").append("-".repeat(width * 4+1)).append("\n");
            for(int j = 0; j < width; j++){
                sortie.append("|").append(this.getCell(i, j).getRepresentation());
                if(j == width - 1){
                    sortie.append("|");
                }
            }
        }
        sortie.append("\n").append("-".repeat(width * 4+1));

        return sortie.toString();
    }

    public Cell getCell(int row, int col){
        return cells[row][col];
    }

    public void setCell(int row, int col, Cell cell){
        cells[row][col] = cell;
    }

    public int getWidth(){
        return cells[0].length;
    }
    public int getHeight(){
        return cells.length;
    }

    public boolean isEmptyCell(int row, int col){
        return cells[row][col].isEmpty();
    }

    public List<int[]> getListPositionCellsEmpty(){
        List<int[]> listCellEmpty = new ArrayList<>();

        for (int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++){
                if(cells[i][j].isEmpty()){
                    listCellEmpty.add(new int[]{i, j});
                }
            }
        }

        return listCellEmpty;
    }

    public List<String> importBoar(){
        List<String> result = new ArrayList<>();

        for (int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++){
                result.add(cells[i][j].getRepresentation());
            }
        }

        return result;
    }

}
