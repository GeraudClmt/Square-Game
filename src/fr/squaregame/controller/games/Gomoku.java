package fr.squaregame.controller.games;

import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;
import fr.squaregame.controller.Game;
import fr.squaregame.model.Board;
import fr.squaregame.model.player.Player;
import fr.squaregame.view.InteractionUtilisateur;
import fr.squaregame.view.View;

import java.util.Random;

public class Gomoku extends Game {
    public Gomoku(){
        super.width = 15;
        super.height = 15;
        super.signList = new String[]{"\u001B[91mX\u001B[0m", "\u001B[94mO\u001B[0m"};
        super.board = new Board(height, width, " ");
        super.winningLength = 5;
        super.countTurnPlayed = 0;
    }

}