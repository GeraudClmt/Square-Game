package fr.squaregame.view;

/**
 * Vue permettant l'affichage des messages vers la sortie standard.
 * Cette classe encapsule System.out pour faciliter d'éventuels changements d'IHM.
 */
public class View {
    /**
     * Affiche un message suivi d'un retour à la ligne.
     *
     * @param message texte à afficher
     */
    public void printMessage(String message){
        System.out.println(message);
    }
}
