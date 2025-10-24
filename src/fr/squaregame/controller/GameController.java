package fr.squaregame.controller;

import fr.squaregame.model.exceptions.BoardIsFull;
import fr.squaregame.model.exceptions.PlayerWin;
import fr.squaregame.model.*;
import fr.squaregame.model.player.ArtificialPlayer;
import fr.squaregame.model.player.HumanPlayer;
import fr.squaregame.model.player.Player;
import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;

import java.util.Random;

import static fr.squaregame.controller.GameState.*;

/**
 * Contrat et logique partagée pour les jeux sur grille (TicTacToe, Gomoku, Puissance 4).
 * Gère les joueurs, le plateau, la boucle de jeu et la détection de fin de partie.
 */
public abstract class GameController {
    private Player player1;
    private Player player2;
    private final Board board;
    private final int width;
    private final int height;
    private int countTurnPlayed;
    private final int winningLength;
    private final String[] signList;
    private GameState currentGameState;
    private InteractionUtilisateur interactionUtilisateur;
    private View view;
    private String signPlayer1;
    private String signPlayer2;
    private Player currentPlayer;
    private int[] currentMove;

    public GameController(int width, int height, String[] signList, int winningLength) {
        this.width = width;
        this.height = height;
        this.signList = signList;
        this.board = new Board(height, width, " ");
        this.winningLength = winningLength;
        countTurnPlayed = 0;
    }

    public void statesMachine() {
        switch (currentGameState) {
            case INIT_GAME -> welcome();
            case TURN -> playerTurn();
            case PLAY_MOVE -> setOwner();
            case CHECK_IF_ENDED -> isOver();
            case NEXT_PLAYER -> nextPlayer();
            case WIN -> win();
            case DRAW -> draw();
        }
        if(currentGameState != QUIT){
            statesMachine();
        }
    }


    public Board getBoard() {
        return board;
    }

    public int getHeight() {
        return height;
    }

    private void setOwner() {
        board.setCell(currentMove[0], currentMove[1], new Cell(currentPlayer.getRepresentation()));
        countTurnPlayed++;
        currentGameState = CHECK_IF_ENDED;
    }

    private void isOver() {
        if (countAlignement(currentMove[0], currentMove[1]) >= winningLength) {
            currentGameState = WIN;
        } else if (countTurnPlayed == width * height) {
            currentGameState = DRAW;
        } else {
            currentGameState = NEXT_PLAYER;
        }
    }

    /**
     * Représentation textuelle du plateau.
     *
     * @return la chaîne représentant l'état courant du plateau
     */
    public String getBoardToString() {
        return board.toString();
    }

    /**
     * Calcule la plus grande longueur d'alignement passant par la case (row, col)
     * dans les 4 directions (horizontale, verticale, et les deux diagonales).
     *
     * @param row ligne de référence
     * @param col colonne de référence
     * @return la longueur maximale d'alignement trouvée
     */
    protected int countAlignement(int row, int col) {
        int maxOccurence = 0;
        int countOccurence = 0;
        int currentRow = row;
        int currentCol = col;
        String playerSign = board.getCell(row, col).getRepresentation();

        //Horizontale
        while (currentCol < width && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)) {
            countOccurence++;
            currentCol++;
        }
        currentCol = col - 1;
        while (currentCol >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)) {
            countOccurence++;
            currentCol--;
        }

        maxOccurence = Math.max(maxOccurence, countOccurence);


        //Vertical
        countOccurence = 0;
        currentCol = col;
        while (currentRow < height && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)) {
            countOccurence++;
            currentRow++;
        }
        currentRow = row - 1;
        while (currentRow >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)) {
            countOccurence++;
            currentRow--;
        }
        maxOccurence = Math.max(maxOccurence, countOccurence);

        //Diagonal
        countOccurence = 0;
        currentRow = row;
        while (currentRow < height && currentCol < width && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)) {
            countOccurence++;
            currentRow++;
            currentCol++;
        }
        currentRow = row - 1;
        currentCol = col - 1;
        while (currentRow >= 0 && currentCol >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)) {
            countOccurence++;
            currentRow--;
            currentCol--;
        }
        maxOccurence = Math.max(maxOccurence, countOccurence);

        //Diagonal
        countOccurence = 0;
        currentRow = row;
        currentCol = col;
        while (currentRow >= 0 && currentCol < width && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)) {
            countOccurence++;
            currentRow--;
            currentCol++;
        }
        currentRow = row + 1;
        currentCol = col - 1;
        while (currentRow < height && currentCol >= 0 && board.getCell(currentRow, currentCol).getRepresentation().equals(playerSign)) {
            countOccurence++;
            currentRow++;
            currentCol--;
        }
        maxOccurence = Math.max(maxOccurence, countOccurence);


        return maxOccurence;
    }

    /**
     * Démarre la boucle de jeu: choix des joueurs, alternance des tours, et détection de fin.
     *
     * @param interactUser utilitaire d'interaction utilisateur
     * @param view         vue d'affichage
     */
    public void start(InteractionUtilisateur interactUser, View view) {
        this.interactionUtilisateur = interactUser;
        this.view = view;
        currentGameState = INIT_GAME;
        statesMachine();
    }

    protected void welcome() {
        view.printMessage("Début du jeu");
        boolean isHumanPlayer1 = interactionUtilisateur.isPositifResponse("Le premier joueur est un humain ? Y / N");
        if (isHumanPlayer1) {
            signPlayer1 = interactionUtilisateur.getSign(signList);
            player1 = new HumanPlayer(signPlayer1);
        } else {
            signPlayer1 = signList[0];
            player1 = new ArtificialPlayer(signPlayer1);
        }

        if (signPlayer1.equals(signList[0])) {
            signPlayer2 = signList[1];
        } else {
            signPlayer2 = signList[0];
        }

        boolean isHumanPlayer2 = interactionUtilisateur.isPositifResponse("Le deuxième joueur est un humain ? Y / N");
        if (isHumanPlayer2) {
            player2 = new HumanPlayer(signPlayer2);
        } else {
            player2 = new ArtificialPlayer(signPlayer2);
        }

        view.printMessage("Joueur 1 : " + signPlayer1 + " et Joueur 2 : " + signPlayer2);
        currentPlayer = player1;
        currentGameState = TURN;
    }

    private void nextPlayer() {
       currentPlayer = currentPlayer == player1 ? player2 : player1;
       currentGameState = TURN;
    }

    private void win() {
        view.printMessage(board.toString());
        view.printMessage("Le joueur " + currentPlayer.getRepresentation() + " gagne");
        currentGameState = QUIT;
    }

    private void draw() {
        view.printMessage(board.toString());
        view.printMessage("Egalité");
        currentGameState = QUIT;
    }


    /**
     * Demande des coordonnées valides à l'utilisateur pour placer un pion.
     *
     * @param interactUser gestionnaire d'interaction
     * @param view         vue d'affichage
     * @return un tableau [ligne, colonne]
     * @throws ArrayIndexOutOfBoundsException si la saisie sort du plateau (gérée par relance)
     */
    protected int[] getCoordinates(InteractionUtilisateur interactUser, View view) throws ArrayIndexOutOfBoundsException {
        int line = interactUser.getInputInt("Entrez une ligne") - 1;
        int column = interactUser.getInputInt("Entrez une colonne") - 1;

        if (!board.getCell(line, column).getRepresentation().equals("   ")) {
            view.printMessage("Case déja prise\n");
            return getCoordinates(interactUser, view);
        } else {
            return new int[]{line, column};
        }
    }

    /**
     * Génère des coordonnées aléatoires valides pour un joueur artificiel.
     *
     * @param interactUser gestionnaire d'interaction (non utilisé ici)
     * @param view         vue d'affichage (non utilisée ici)
     * @return un tableau [ligne, colonne]
     * @throws ArrayIndexOutOfBoundsException si la case tirée est invalide (relance jusqu'à valide)
     */
    protected int[] getCoordinatesForArtificialPlayer(InteractionUtilisateur interactUser, View view) throws ArrayIndexOutOfBoundsException {
        Random rand = new Random();
        int row = rand.nextInt(0, board.getHeight());
        int col = rand.nextInt(0, board.getWidth());

        if (board.isEmptyCell(row, col)) {
            return new int[]{row, col};
        }

        return getCoordinatesForArtificialPlayer(interactUser, view);
    }


    private void playerTurn() throws PlayerWin, BoardIsFull {
        view.printMessage(board.toString());
        view.printMessage("\nAu tour du joueur " + currentPlayer.getRepresentation());
        currentMove = null;
        try {
            if (currentPlayer instanceof ArtificialPlayer) {
                currentMove = getCoordinatesForArtificialPlayer(interactionUtilisateur, view);
            } else {
                currentMove = getCoordinates(interactionUtilisateur, view);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            view.printMessage("Coordonnées en dehors du plateau\n");
        }

        if (currentMove != null) {
            currentGameState = PLAY_MOVE;
        }
    }

}