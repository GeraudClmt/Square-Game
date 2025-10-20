package fr.squaregame.components;

public class Board {
    private final Cell[][] cells;

    public Board(int height, int width){
        cells = new Cell[height][width];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                cells[i][j] = new Cell();
            }
        }
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
}
