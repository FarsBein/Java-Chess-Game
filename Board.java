import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class Board {
    // This is a TreeMap inside a TreeMap. Consider the first integer as x, and the
    // second as y.
    private TreeMap<Integer, Piece> BoardLocations; // The pieces will be presented by a unique string for now.
    private LinkedList<Piece> DeadPieces;
    EmptyPiece empty;

    /**
     * Constructor of the Board.
     */
    public Board() {
        // Initializing the fields.
        BoardLocations = new TreeMap<Integer, Piece>();
        DeadPieces = new LinkedList<Piece>();
        empty = new EmptyPiece();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                int positionKey = (i * 10) + j;
                // The way the key works is as follows: for every x, multiply x byt 10 and add
                // by y. This will make each integer unique given 2 integer inputs.
                // For example: position (1,2) will be 12 but (2,1) will be 21. Thus the order
                // givens unique values.
                // This is done for memory purposes.

                // Placing Pawns:
                if (i == 2) { // If the row is 2.
                    Pawn whitePawn = new Pawn(true, positionKey);
                    BoardLocations.put(positionKey, whitePawn);
                } else if (i == 7) { // If the row is 7.
                    Pawn blackPawn = new Pawn(false, positionKey);
                    BoardLocations.put(positionKey, blackPawn);
                } else { // In all other cases, put empty.
                    BoardLocations.put(positionKey, empty);
                }
                // Placing Castles:
                if (i == 1 && (j == 1 || j == 8)) { // If (1,1) or (1,8)
                    Castle whiteCastle = new Castle(true, positionKey);
                    BoardLocations.put(positionKey, whiteCastle);
                } else if (i == 8 && (j == 1 || j == 8)) { // If (8,1) or (8,8)
                    Castle blackCastle = new Castle(false, positionKey);
                    BoardLocations.put(positionKey, blackCastle);
                }

                // Placing Bishops:
                if (i == 1 && (j == 3 || j == 6)) {
                    Bishop whiteBishop = new Bishop(true, positionKey);
                    BoardLocations.put(positionKey, whiteBishop);
                } else if (i == 8 && (j == 3 || j == 6)) {
                    Bishop blackBishop = new Bishop(false, positionKey);
                    BoardLocations.put(positionKey, blackBishop);
                }

                // Placing Queen:
                if (i == 1 && (j == 5)) {
                    Queen whiteQueen = new Queen(true, positionKey);
                    BoardLocations.put(positionKey, whiteQueen);
                } else if (i == 8 && (j == 5)) {
                    Queen blackQueen = new Queen(false, positionKey);
                    BoardLocations.put(positionKey, blackQueen);
                }

                // Placing King:
                if (i == 1 && (j == 4)) {
                    King whiteKing = new King(true, positionKey);
                    BoardLocations.put(positionKey, whiteKing);
                } else if (i == 8 && (j == 4)) {
                    King blackKing = new King(false, positionKey);
                    BoardLocations.put(positionKey, blackKing);
                }

                // Placing Knight:
                if (i == 1 && (j == 2 || j == 7)) {
                    Knight whiteKnight = new Knight(true, positionKey);
                    BoardLocations.put(positionKey, whiteKnight);
                } else if (i == 8 && (j == 2 || j == 7)) {
                    Knight blackKnight = new Knight(false, positionKey);
                    BoardLocations.put(positionKey, blackKnight);
                }
            }
        }
    }

    /**
     * Prints the board.
     */
    public void printBoard() {
        for (int i = 8; i > 0; i--) { // Looping from 8 downwards since we start printing the last line first.
            printLine(i); // Printing the line.
            System.out.println(); // Printing a new line
        }
        System.out.println("   - - - - - - - - - - - -");
        System.out.println("   1  2  3  4  5  6  7  8");
    }

    // Since each line can be denoted by its number. This helper method prints a
    // line in the board given the line number.
    private void printLine(int lineNumber) {
        System.out.print(lineNumber + "| "); // Printing the line number at the left of the board.
        for (int j = 1; j < 9; j++) { // Looping through each y value.
            System.out.print(BoardLocations.get(lineNumber * 10 + j).getHead() + " ");
        }
    }

    /**
     *
     * moves a piece if possible given its location to a new location and replaces
     * the current location with "." empty space. Will throw an exception if xNew
     * and yNew have illegal integers based of the piece in the xCurrent, yCurrent
     * piece.
     * 
     * @param xCurrent
     * @param yCurrent
     * @param xNew
     * @param yNew
     * @throws IllegalAccessError
     */
    public void movePiece(int Current, int New) throws IllegalAccessError {
        Piece SelectedPiece = BoardLocations.get(Current);
        if (SelectedPiece.canMove(New)) {
            Piece toBeReplacedPiece = BoardLocations.get(New);
            // Checking for dead pieces.
            if (toBeReplacedPiece.getColor() == SelectedPiece.getColor()) { // If the selected piece has the same color
                                                                            // as the other piece.
                throw new IllegalAccessError("Cannot eat friendly pieces.");
            } else if (!toBeReplacedPiece.getHead().equals(empty.getHead())) { // If the piece to be replaced ISNOT
                                                                               // empty:
                toBeReplacedPiece.setPosition(0); // BoardLocations of all dead pieces is zero.
                DeadPieces.add(toBeReplacedPiece);
            }
            BoardLocations.replace(Current, empty);
            BoardLocations.replace(New, SelectedPiece);
        } else { // Will throw an exception if
            throw new IllegalAccessError("Move is illegal.");
        }
    }

    /**
     * 
     * @return The set of dead pieces of the board thus far.
     */
    public LinkedList<Piece> getDeadPieces() {
        return DeadPieces;
    }
}

class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("type whatever|> ");
        System.out.println(scanner.hasNext()&&!scanner.hasNextInt());
        // Board b = new Board();
        // b.printBoard();
        // System.out.println("----------------------");
        // System.out.println("Moving (8, 8) to (8, 7)");
        // System.out.println("----------------------");
        // //b.movePiece(8, 8, 8, 2);
        // b.printBoard();
        // System.out.println("----------------------");
        // System.out.println("Moving (7, 8) to (6, 8)");
        // System.out.println("----------------------");
        // //b.movePiece(7, 8, 6, 8);
        // //b.printBoard();
        // System.out.println(b.getDeadPieces());
    }
}