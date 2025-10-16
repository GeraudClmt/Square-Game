package fr.squaregame.tictactoe;

import fr.squaregame.components.Cell;
import fr.squaregame.components.InputOutput;
import fr.squaregame.components.Player;
import fr.squaregame.exceptions.BoardIsFull;
import fr.squaregame.exceptions.PlayerWin;

public class TicTacToe {
    private final int size;
    private Player player1;
    private Player player2;
    private Cell[][] tableCells;
    private int countTurnPlayed;

    public TicTacToe(){
        this.size = 3;
        countTurnPlayed = 0;

        this.tableCells = new Cell[this.size][this.size];
        for(int i = 0; i < tableCells.length; i++){
            for(int j = 0; j < tableCells.length; j++){
                tableCells[i][j] = new Cell();
            }
        }

    }

    public String display(){
        StringBuilder sortie = new StringBuilder();
        for(int i = 0; i < tableCells.length; i++){
            sortie.append("\n").append("-".repeat(size * 4+1)).append("\n");
            for(int j = 0; j < tableCells.length; j++){
                sortie.append("|").append(tableCells[i][j].getRepresentation());
                if(j == size - 1){
                    sortie.append("|");
                }
            }
        }
        sortie.append("\n").append("-".repeat(size * 4+1));
        return sortie.toString();
    }

    public int[] getMoveFromPlayer(InputOutput inputOutput) throws ArrayIndexOutOfBoundsException{
        int line = -1;
        int column = -1;

        while(line == -1 || column == -1 || !tableCells[line][column].getRepresentation().equals("   ")){
            line = inputOutput.getInputInt("Entrez une ligne") - 1;
            column = inputOutput.getInputInt("Entrez une colone") - 1;

            if(!tableCells[line][column].getRepresentation().equals("   ")){
                inputOutput.printMessage("Case déja prise\n");
            }
        }
        return new int[]{line, column};
    }

    public void setOwner(int x, int y, Player player){
        tableCells[x][y] = new Cell(player.getRepresentation());
        countTurnPlayed++;
    }

    public void isOver(String playerSign)throws PlayerWin, BoardIsFull {

        for(int line = 0; line < size; line++){
            if(tableCells[line][0].getRepresentation().equals(playerSign) &&
                    tableCells[line][1].getRepresentation().equals(playerSign) &&
                    tableCells[line][2].getRepresentation().equals(playerSign)){
                throw new PlayerWin("Le joueur a gagné");
            }
        }

        for(int column = 0; column < size; column++){
            if(tableCells[0][column].getRepresentation().equals(playerSign) &&
                    tableCells[1][column].getRepresentation().equals(playerSign) &&
                    tableCells[2][column].getRepresentation().equals(playerSign)){
                throw new PlayerWin("Le joueur a gagné");
            }
        }

        if(tableCells[0][0].getRepresentation().equals(playerSign)&&
                tableCells[1][1].getRepresentation().equals(playerSign)&&
                tableCells[2][2].getRepresentation().equals(playerSign)){
            throw new PlayerWin("Le joueur a gagné");
        }

        if(tableCells[0][2].getRepresentation().equals(playerSign)&&
                tableCells[1][1].getRepresentation().equals(playerSign)&&
                tableCells[2][0].getRepresentation().equals(playerSign)){
            throw new PlayerWin("Le joueur a gagné");
        }

        if(countTurnPlayed == size*size){
            throw new BoardIsFull("Le plateau est complet");
        }
    }

    public void playerTurn(Player player, InputOutput inputOutput)throws PlayerWin, BoardIsFull{
        int[] coordinate = new int[]{};
        while(coordinate.length < 2){
            try{
                coordinate = getMoveFromPlayer(inputOutput);
            } catch (ArrayIndexOutOfBoundsException e) {
                inputOutput.printMessage("Coordonnées en dehors du plateau\n");
            }
        }

        setOwner(coordinate[0], coordinate[1], player);
        inputOutput.printMessage(display());

        isOver(" "+ player.getRepresentation() + " ");
    }

    public void play(InputOutput inputOutput){
        inputOutput.printMessage(display());

        boolean isPlay = true;

        String sign = inputOutput.getSign();
        String sign2;
        player1 = new Player(sign);
        if(sign.equals("X")){
            sign2 = "O";

        }else{
            sign2 = "X";
        }
        player2 = new Player(sign2);
        inputOutput.printMessage("Joueur 1 : " + sign + " et Joueur 2 : " + sign2);


        while(isPlay){
            try{
                playerTurn(player1, inputOutput);
                playerTurn(player2, inputOutput);

            } catch (PlayerWin e) {
                inputOutput.printMessage(e.getMessage());
                isPlay = false;
            } catch (BoardIsFull e){
                inputOutput.printMessage(e.getMessage());
                isPlay =  false;
            }
        }

    }
}
