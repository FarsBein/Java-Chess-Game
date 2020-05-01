public class ChessPieces {
    private int[] startPoint;
    private int[] endPoint;
    private String[][] board;
    public ChessPieces(){
        startPoint = new int[2];
        endPoint = new int[2];
        board = new String[8][8];
    }

    public int[] getStartPoint(){
        return startPoint;
    }
    public int[] getEndPoint(){
        return endPoint;
    }
    public void printBoard(){
        System.out.print("\n\n");
        for (int i=0; i < board.length; i++){
            for (int j=0; j < board[0].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }
    // for testing --------------------
    public void resetBoard(){
        for (int i=0; i < board.length; i++){
            for (int j=0; j < board[0].length; j++){
                board[i][j]=" . ";
            }
            System.out.print("\n");
        }

        // black pieces
        board[1][0]=" O "; board[1][1]=" O "; board[1][2]=" O "; board[1][3]=" O ";
        board[1][4]=" O "; board[1][5]=" O "; board[1][6]=" O "; board[1][7]=" O ";

        // white pieces
        board[6][0]=" X "; board[6][1]=" X "; board[6][2]=" X "; board[6][3]=" X ";
        board[6][4]=" X "; board[6][5]=" X "; board[6][6]=" X "; board[6][7]=" X ";

    }

    public void createPiece(int[] loc){
        board[loc[1]][loc[0]] = " # ";
    }
    // ------------------------------- endTest

    public boolean pawn(String color,int[] startPoint, int[] endPoint){
        if(color == "white" || color == "w"){
            if (endPoint[1] + 1 == startPoint[1]||(startPoint[1] == 6 && endPoint[1] + 2 == startPoint[1])){
                if (endPoint[0] == startPoint[0] || endPoint[0]+1 == startPoint[0] || endPoint[0]-1 == startPoint[0]){
                    
                    //testing -------
                    board[startPoint[1]][startPoint[0]] = " . ";
                    board[endPoint[1]][endPoint[0]] = " X ";
                    //--------------- endTest

                    return true;
                }
            } 
        } else {
            if (endPoint[1] - 1 == startPoint[1] ||(startPoint[1] == 1 && endPoint[1] - 2 == startPoint[1])){
                if (endPoint[0] == startPoint[0] || endPoint[0]+1 == startPoint[0] || endPoint[0]-1 == startPoint[0]){
                    
                    //testing -------
                    board[startPoint[1]][startPoint[0]] = " . ";
                    board[endPoint[1]][endPoint[0]] = " O ";
                    //--------------- endTest

                    return true;
                }
            }
        }

        //testing -------
        board[endPoint[1]][endPoint[0]] = " ? ";
        //--------------- endTest

        return false;
    }

}