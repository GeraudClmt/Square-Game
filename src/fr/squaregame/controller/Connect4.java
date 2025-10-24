package fr.squaregame.controller;

import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;

import java.util.Random;

/**
 * Implémentation du Puissance 4 sur une grille 7x6 avec gravité verticale des pions.
 */
public class Connect4 extends Game {
    private final Random rand = new Random();

    /**
     * Initialise une partie de Puissance 4 (7 colonnes, 6 lignes) et une condition de victoire à 4 alignés.
     */
    public Connect4(){
        super(7, 6, new String[]{"\u001B[91m●\u001B[0m", "\u001B[93m●\u001B[0m"}, 4);
    }

    /**
     * Récupère une colonne valide et renvoie la position où le pion tombera.
     *
     * @param interactUser gestionnaire d'interaction
     * @param view vue d'affichage
     * @return un tableau [ligne, colonne]
     * @throws ArrayIndexOutOfBoundsException si la saisie sort du plateau (gérée par relance)
     */
    @Override
    public int[] getCoordinates(InteractionUtilisateur interactUser, View view) throws ArrayIndexOutOfBoundsException {
        int column = interactUser.getInputInt("Entrez une colonne") - 1;

        if(!getBoard().getCell(0, column).getRepresentation().equals("   ")){
            view.printMessage("Colonne pleine\n");
            return getCoordinates(interactUser, view);
        }else{
            return new int[]{getRow(column), column};
        }
    }

    /**
     * Génère un coup valide pour l'IA en choisissant une colonne non pleine.
     */
    @Override
    public int[] getCoordinatesForArtificialPlayer(InteractionUtilisateur interactUser, View view) throws ArrayIndexOutOfBoundsException {
        int col = rand.nextInt(0, getBoard().getWidth());

        if (getBoard().getCell(0, col).getRepresentation().equals("   ")) {
            return new int[]{getRow(col), col};
        }

        return getCoordinatesForArtificialPlayer(interactUser, view);
    }

    /**
     * Calcule la ligne d'atterrissage du pion dans une colonne donnée.
     *
     * @param col index de la colonne
     * @return la ligne disponible la plus basse, ou -1 si la colonne est pleine
     */
    private int getRow(int col){
        for(int i = getHeight()-1; i >= 0; i--){
            if(getBoard().getCell(i, col).getRepresentation().equals("   ")){
                return i;
            }
        }
        return -1;
    }
}