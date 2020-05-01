import java.util.Arrays;

public class testChess {
    public static void main(String[] args) {
        int[] start;
        int[] end;
        int[] newPiece;
        String color;
        ChessPieces chessPieces = new ChessPieces();
        //System.out.println(Arrays.toString(chessPieces.getStartPoint()));
        //System.out.println(Arrays.toString(chessPieces.getEndPoint()));
        chessPieces.resetBoard();
        chessPieces.printBoard();

        start = new int[] {4,1};
        end = new int[] {4,2};
        color = "black";
        chessPieces.pawn(color,start,end);
        chessPieces.printBoard();

        start = new int[] {4,6};
        end = new int[] {4,5};
        color = "white";
        chessPieces.pawn(color,start,end);
        chessPieces.printBoard();

    }
}