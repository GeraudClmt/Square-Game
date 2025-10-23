package fr.squaregame.model;

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
     * @param row numéro de la ligne
     * @param col numéro de colonne
     * @return une cellule en fonction des coordonnées
     */
    public Cell getCell(int row, int col){
        return cells[row][col];
    }

    /**
     * Remplace la cellule aux coordonnées données par une nouvelle cellule.
     * @param row numéro de la ligne
     * @param col numéro de colonne
     * @param cell nouvelle céllule
     */
    public void setCell(int row, int col, Cell cell){
        cells[row][col] = cell;
    }

    /**
     * Largeur du plateau (nombre de colonnes).
     * @return la largeur du board
     */
    public int getWidth(){
        return cells[0].length;
    }
    /**
     * Hauteur du plateau (nombre de lignes).
     * @return la hauteur tu board
     */
    public int getHeight(){
        return cells.length;
    }

    /**
     * Indique si la cellule (row, col) est vide.
     * @param row numéro de ligne
     * @param col numéro de colonne
     * @return true si la cellule est vide
     */
    public boolean isEmptyCell(int row, int col){
        return cells[row][col].isEmpty();
    }
}
