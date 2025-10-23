package fr.squaregame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un plateau de jeu rectangulaire composé de cellules.
 */
public class Board {
    private final Cell[][] cells;

    /**
     * Construit un plateau de dimensions données et initialise toutes les cellules avec une représentation d'absence de pion.
     *
     * @param height nombre de lignes
     * @param width nombre de colonnes
     * @param emptyCell représentation pour une cellule vide (sera entourée d'espaces par Cell)
     */
    public Board(int height, int width, String emptyCell){
        cells = new Cell[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                cells[i][j] = new Cell(emptyCell);
            }
        }
    }

    /**
     * Construit une chaîne représentant graphiquement le plateau avec bordures.
     */
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

    /**
     * Récupère la cellule aux coordonnées données.
     */
    public Cell getCell(int row, int col){
        return cells[row][col];
    }

    /**
     * Remplace la cellule aux coordonnées données par une nouvelle cellule.
     */
    public void setCell(int row, int col, Cell cell){
        cells[row][col] = cell;
    }

    /**
     * Largeur du plateau (nombre de colonnes).
     */
    public int getWidth(){
        return cells[0].length;
    }
    /**
     * Hauteur du plateau (nombre de lignes).
     */
    public int getHeight(){
        return cells.length;
    }

    /**
     * Indique si la cellule (row, col) est vide.
     */
    public boolean isEmptyCell(int row, int col){
        return cells[row][col].isEmpty();
    }

    /**
     * Liste les positions [row, col] de toutes les cellules vides.
     */
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

    /**
     * Exporte une vue linéarisée des représentations des cellules du plateau.
     *
     * @return liste des représentations de toutes les cellules, parcourues ligne par ligne
     */
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
