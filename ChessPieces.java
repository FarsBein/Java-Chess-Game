import java.util.ArrayList;

// "for testing" means it will be removed

public class ChessPieces {
    //for testing--------------
    private int[] startPoint; // Piece current position in the board array, x is index 0 and y is index 1
    private int[] endPoint;  // final position, destination
    private String[][] board;
    // ------------------------

    // this can be empty
    public ChessPieces(){
        //for testing--------------
        startPoint = new int[2];
        endPoint = new int[2];
        board = new String[8][8];
        // ------------------------
    }

    // for testing --------------------
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
    //---------------------------------


    // for testing --------------------
    public void resetBoard(){ // adds symbols to the empty board for testing 
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

    // add pieces if need it for testing
    public void createPiece(int[] loc){
        board[loc[1]][loc[0]] = " # ";
    }

    // ------------------------------- 


    //pawn 
    //1// checks if the piece is white because they face different side of the board and require different calculations
    //2// to move a pawn foreword from the white side you -1 so endpoint[1](y) + 1 will get us back to the starting position startPoint[1]
    //3// pawns can move in an angle to take a piece but only forward so check if endPoint[0] -1 or +1 or +0 get us back to startPoint
    //4// startPoint[1] == 6 && endPoint[1] + 2 == startPoint[1] checks if the pawn is in the first row if yes it can move +2, fist row for white is 6 and black is 1
    //5// black same thing but because they face different side of the board they require the opposite calculations
    public boolean pawn(String color,int[] startPoint, int[] endPoint){
        if(color == "white" || color == "w"){ //1//
            if (endPoint[1] + 1 == startPoint[1]){ //2//
                if (endPoint[0] == startPoint[0] || endPoint[0]+1 == startPoint[0] || endPoint[0]-1 == startPoint[0]){//3//
                    
                    //testing -------
                    board[startPoint[1]][startPoint[0]] = " . ";
                    board[endPoint[1]][endPoint[0]] = " X ";
                    //--------------- 

                    return true;
                }
            }
            if(startPoint[1] == 6 && endPoint[1] + 2 == startPoint[1]){//4//

                //testing ------- 
                board[startPoint[1]][startPoint[0]] = " . ";
                board[endPoint[1]][endPoint[0]] = " X ";
                //--------------- 

                return true;
            }
        } else {//5//
            if (endPoint[1] - 1 == startPoint[1]){
                if (endPoint[0] == startPoint[0] || endPoint[0]+1 == startPoint[0] || endPoint[0]-1 == startPoint[0]){
                    
                    //testing -------
                    board[startPoint[1]][startPoint[0]] = " . ";
                    board[endPoint[1]][endPoint[0]] = " O ";
                    //--------------- endTest

                    return true;
                }
            }
            if(startPoint[1] == 1 && endPoint[1] - 2 == startPoint[1]){

                //testing ------- 
                board[startPoint[1]][startPoint[0]] = " . ";
                board[endPoint[1]][endPoint[0]] = " X ";
                //--------------- 

                return true;
            }
        }

        //testing -------
        board[endPoint[1]][endPoint[0]] = " ? ";
        //--------------- endTest

        return false;
    }


    //bishops
    //1// x1 - x2 = y1 - y2 most be equal since  bishops moves side way ( you can move side way only by adding an equal amount to both y and x)
    public boolean bishops(String color,int[] startPoint, int[] endPoint){
        if (Math.abs(startPoint[0]-endPoint[0]) == Math.abs(startPoint[1]-endPoint[1])){ //1//
            
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


    //Castle
    // castle can  move in the x OR y direction so it will have to maintain one of its values x or y
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

    //queen
    // queen is basically castle and bishops smooched together 
    // but it can only move in one direction at a time 
    // so I check if it moves using Castle or bishops path
    public boolean queen(String color,int[] startPoint, int[] endPoint){
        if (Castle(color,startPoint,endPoint) || bishops(color,startPoint,endPoint)){
            return true;
        }
        return false;
    }

    //king
    // could not come up with an optimized algorithm so i just checked for all 8 possible distentions
    public boolean king(String color,int[] startPoint, int[] endPoint){ 
        if (endPoint[0]+1 == startPoint[0] || endPoint[0]-1 == startPoint[0] || endPoint[0] == startPoint[0]){
            if(endPoint[1]+1 == startPoint[1] || endPoint[1]-1 == startPoint[1] || endPoint[1] == startPoint[1]){
                return true;
            }
        }
        return false;
    }

    //knight
    // since knight has only 8 possible distentions like the king I checked for all of them
    //1// if knight moves x+3 or x-3 away form starting point 
    //2// then y has to be y+1 or y-1 away from starting point 
    //3// if knight moves y+3 or y-3 away form starting point
    //4// then x has to be x+1 or x-1 away from starting point 
    public boolean knight(String color,int[] startPoint, int[] endPoint){ 
        if (endPoint[0]-3 == startPoint[0] || endPoint[0]+3 == startPoint[0]){//1//
            if (endPoint[1]-1 == startPoint[1] || endPoint[1]+1 == startPoint[1]){//2//
                return true;
            }
        }
        if (endPoint[1]-3 == startPoint[1] || endPoint[1]+3 == startPoint[1]){//3//
            if (endPoint[0]-1 == startPoint[0] || endPoint[0]+1 == startPoint[0]){//4//
                return true;
            }
        }
        return false;
    }



}
