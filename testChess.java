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


        start = new int[] {2,0};
        end = new int[] {5,3};
        color = "black";
        chessPieces.bishops(color,start,end);
        chessPieces.printBoard();

        start = new int[] {5,7};
        end = new int[] {2,4};
        color = "white";
        chessPieces.bishops(color,start,end);
        chessPieces.printBoard();


        start = new int[] {0,0};
        end = new int[] {0,4};
        color = "black";
        chessPieces.Castle(color,start,end);
        chessPieces.printBoard();

        start = new int[] {7,7};
        end = new int[] {4,7};
        color = "white";
        chessPieces.Castle(color,start,end);
        chessPieces.printBoard();

    }
}