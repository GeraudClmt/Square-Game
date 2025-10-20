package fr.squaregame.components;

public class View {
    public void printMessage(String message){
        System.out.println(message);
    }

    public void display(Board board){
        int width = board.getWidth();
        int height = board.getHeight();
        StringBuilder sortie = new StringBuilder();

        for(int i = 0; i < height; i++){
            sortie.append("\n").append("-".repeat(width * 4+1)).append("\n");
            for(int j = 0; j < width; j++){
                sortie.append("|").append(board.getCell(i, j).getRepresentation());
                if(j == width - 1){
                    sortie.append("|");
                }
            }
        }
        sortie.append("\n").append("-".repeat(width * 4+1));
        printMessage(sortie.toString());
    }
}
