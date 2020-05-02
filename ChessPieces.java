public class ChessPieces {
    private int[] startPoint;
    private int[] endPoint;
    private String[][] board; //for testing
    public ChessPieces(){
        startPoint = new int[2];
        endPoint = new int[2];
        board = new String[8][8];//for testing
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

        // black pawn pieces
        board[1][0]=" O "; board[1][1]=" O "; board[1][2]=" O "; board[1][3]=" O ";
        board[1][4]=" O "; board[1][5]=" O "; board[1][6]=" O "; board[1][7]=" O ";
        board[0][2]=" B "; board[0][5]=" B "; //black bishops
        board[0][0]=" C "; board[0][7]=" C "; //black Castles
        board[0][1]=" K "; board[0][6]=" K "; //black knight

        // white pawn pieces
        board[6][0]=" X "; board[6][1]=" X "; board[6][2]=" X "; board[6][3]=" X ";
        board[6][4]=" X "; board[6][5]=" X "; board[6][6]=" X "; board[6][7]=" X ";
        board[7][2]=" B "; board[7][5]=" B "; //white bishops
        board[0][0]=" C "; board[0][7]=" C "; //white Castles
        board[7][1]=" K "; board[7][6]=" K "; //white knight



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

    public boolean bishops(String color,int[] startPoint, int[] endPoint){
        if (Math.abs(startPoint[0]-endPoint[0]) == Math.abs(startPoint[1]-endPoint[1])){ // x1 - x2 and y1 - y2 most be equal and whole numbers
            //testing -------
            board[startPoint[1]][startPoint[0]] = " . ";
            board[endPoint[1]][endPoint[0]] = " B ";
            //--------------- endTest
            return true;
        }

        //testing -------
        board[endPoint[1]][endPoint[0]] = " ? ";
        //--------------- endTest

        return false;
    }

    public boolean Castle(String color,int[] startPoint, int[] endPoint){
        
        if (startPoint[0] == endPoint[0] || startPoint[1] == endPoint[1]) {
            //testing -------
            board[startPoint[1]][startPoint[0]] = " . ";
            board[endPoint[1]][endPoint[0]] = " C ";
            //--------------- endTest
            return true;
        }

        //testing -------
        board[endPoint[1]][endPoint[0]] = " ? ";
        //--------------- endTest

        return false;
    }

    public boolean queen(String color,int[] startPoint, int[] endPoint){
        if (Castle(color,startPoint,endPoint) || bishops(color,startPoint,endPoint)){
            return true;
        }
        return false;
    }

    public boolean king(String color,int[] startPoint, int[] endPoint){ 
        if (endPoint[0]+1 == startPoint[0] || endPoint[0]-1 == startPoint[0] || endPoint[0] == startPoint[0]){
            if(endPoint[1]+1 == startPoint[1] || endPoint[1]-1 == startPoint[1] || endPoint[1] == startPoint[1]){
                return true;
            }
        }
        return false;
    }

    public boolean knight(String color,int[] startPoint, int[] endPoint){ //not sure if there is an optimized equation
        if (endPoint[0]-3 == startPoint[0] || endPoint[0]+3 == startPoint[0]){
            if (endPoint[1]-1 == startPoint[1] || endPoint[1]+1 == startPoint[1]){
                return true;
            }
        }
        if (endPoint[1]-3 == startPoint[1] || endPoint[1]+3 == startPoint[1]){
            if (endPoint[0]-1 == startPoint[0] || endPoint[0]+1 == startPoint[0]){
                return true;
            }
        }
        return false;
    }

}