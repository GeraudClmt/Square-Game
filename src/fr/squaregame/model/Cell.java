package fr.squaregame.model;

/**
 * Représente une cellule du plateau contenant la représentation d'un pion ou vide.
 */
public class Cell {
    /** Représentation textuelle stockée avec un padding d'espaces pour l'affichage. */
    private final String representation;

    /**
     * Construit une cellule avec la représentation donnée. Un espace est ajouté avant et après pour l'affichage.
     *
     * @param representation chaîne représentant le contenu (pion ou vide)
     */
    public Cell(String representation){
        this.representation = " " + representation + " ";
    }

    /**
     * Retourne la représentation textuelle de la cellule.
     * @return la représentation de la cellule
     */
    public String getRepresentation(){
        return representation;
    }

    /**
     * Indique si la cellule est vide.
     * @return true si la cellule est vide
     */
    public boolean isEmpty(){
        return representation.equals("   ");
    }
}
