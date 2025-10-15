package fr.squaregame.tictactoe;

import fr.squaregame.components.Cell;
import fr.squaregame.components.InputOutput;
import fr.squaregame.components.Player;

public class TicTacToe {
    private final int size;
    private final Player player1;
    private final Player player2;
    private Cell[][] tableCells;

    public TicTacToe(Player player1, Player player2){
        this.size = 3;
        this.player1 = player1;
        this.player2 = player2;

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
    }



    public void play(InputOutput inputOutput){
        inputOutput.printMessage(display());
        int[] coordinate = new int[]{};

        while(coordinate.length < 2){
            try{
                coordinate = getMoveFromPlayer(inputOutput);
            } catch (ArrayIndexOutOfBoundsException e) {
                inputOutput.printMessage("Coordonnées en dehors du plateau\n");
            }
        }
        setOwner(coordinate[0], coordinate[1], player1);
        inputOutput.printMessage(display());

        coordinate = new int[]{};
        while(coordinate.length < 2){
            try{
                coordinate = getMoveFromPlayer(inputOutput);
            } catch (ArrayIndexOutOfBoundsException e) {
                inputOutput.printMessage("Coordonnées en dehors du plateau\n");
            }
        }
        setOwner(coordinate[0], coordinate[1], player2);
        inputOutput.printMessage(display());

    }
}
