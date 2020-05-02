import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Board {
    // This is a TreeMap inside a TreeMap. Consider the first integer as x, and the
    // second as y.
    private TreeMap<Integer, String> Positions; // The pieces will be presented by a unique string for now.
    private Set<String> DeadPieces;

    /**
     * Constructor of the Board.
     */
    public Board() {
        // Initializing the Positions TreeMap.
        Positions = new TreeMap<Integer, String>();
        DeadPieces = new HashSet<String>();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                int keyInteger = (i * 10) + j;
                // The way the key works is as follows: for every x, multiply x byt 10 and add
                // by y. This will make each integer unique given 2 integer inputs.
                // For example: position (1,2) will be 12 but (2,1) will be 21. Thus the order
                // givens unique values.
                // This is done for memory purposes.

                // The way this will be done for now is "O" pieces will be placed in even x and
                // y values, and "." in the others.
                if (i % 2 == 0 && j % 2 == 0) {
                    Positions.put(keyInteger, "O");
                } else {
                    Positions.put(keyInteger, ".");
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
        System.out.println("   ---------------");
        System.out.println("   1 2 3 4 5 6 7 8");
    }

    // Since each line can be denoted by its number. This helper method prints a
    // line in the board given the line number.
    private void printLine(int lineNumber) {
        System.out.print(lineNumber + "| "); // Printing the line number at the left of the board.
        for (int j = 1; j < 9; j++) { // Looping through each y value.
            System.out.print(Positions.get(lineNumber * 10 + j) + " ");
        }
    }

    /**
     * moves a piece given its location to a new location and replaces the current
     * location with "." empty space.
     * 
     * @param xCurrent current x location.
     * @param yCurrent current y location.
     * @param xNew     new x location.
     * @param yNew     new y location.
     */
    public void movePiece(int xCurrent, int yCurrent, int xNew, int yNew) {
        int oldPos = xCurrent * 10 + yCurrent;
        int newPos = xNew * 10 + yNew;
        String SelectedPiece = Positions.get(oldPos);
        String toBeReplacedPiece = Positions.get(newPos);
        // Checking for dead pieces.
        if (!toBeReplacedPiece.equals(".")) { // If the piece to be replaced ISNOT empty:
            DeadPieces.add(toBeReplacedPiece);
        }
        Positions.replace(oldPos, ".");
        Positions.replace(newPos, SelectedPiece);
    }

    /**
     * 
     * @return The set of dead pieces of the board thus far.
     */
    public Set<String> getDeadPieces() {
        return DeadPieces;
    }
}

class test {
    public static void main(String[] args) {
        Board b = new Board();
        b.printBoard();
        System.out.println("----------------------");
        System.out.println("Moving (8, 8) to (1, 1)");
        System.out.println("----------------------");
        b.movePiece(8, 8, 1, 1);
        b.printBoard();
        System.out.println("----------------------");
        System.out.println("Moving (4, 4) to (2, 2) and seeing dead pieces");
        System.out.println("----------------------");
        System.out.println("Before: " + b.getDeadPieces());
        b.movePiece(4, 4, 2, 2);
        System.out.println("After: " + b.getDeadPieces());
        b.printBoard();
    }
}