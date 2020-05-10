import java.util.Scanner;

public class GamePlay {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        Boolean color = true;
        board.printBoard();
        System.out.print("1.write the coordinates in two int form start(xy) and end(xy) e.g. 23 24\n2.type q to quit the game\nFirst move for white: ");
        while (scanner.hasNextLine()) {
            if (scanner.hasNext()&&!scanner.hasNextInt()&&scanner.next().equals("q")){
                break;
            }
            int startPoint = scanner.nextInt();
            int endPoint = scanner.nextInt();
            board.movePiece(startPoint, endPoint);
            board.printBoard();
            color = !color;
            if (color) System.out.print("\nWhite turn: ");
            else System.out.print("\nBlack turn: ");
        }
        System.out.println("Game Over!");
    }
}