abstract class Piece {
    protected Boolean color; // 1 for White, 0 for Black
    protected String head; // Used for printing. Can be removed in the app.
    protected int position; // To keep track of the current position of the piece on the board.

    public void setPosition(int BoardPosition) {
        this.position = BoardPosition;
    }

    public Boolean getColor() {
        return this.color;
    }

    public String getHead() {
        return this.head;
    }

    public String toString() {
        return this.getHead();
    }

    public int getX(int num){
        return (num/10)%10;
    }

    public int getY(int num){
        return num-((num/10)%10)*10;
    }

    // Abstract methods all classes must have:
    abstract Boolean canMove(int newPosition);

}

class EmptyPiece extends Piece {
    public EmptyPiece() {
        this.head = " . ";
        this.color = null; // An empty position has a null color.
    }

    Boolean canMove(int newPosition) {
        return false;
    }
}

class Pawn extends Piece {

    public Pawn(Boolean assignedColor, int currentPosition) {
        this.color = assignedColor;
        if (assignedColor) { // If white:
            this.head = "PW";
        } else {
            this.head = "PB";
        }

        setPosition(currentPosition); // Give location for the Piece.
    }

    @Override
    Boolean canMove(int newPosition) {
        int x1 = getX(position); // current position
        int y1 = getY(position);
        int x2 = getX(newPosition); // new position
        int y2 = getY(newPosition);

        if(color){ //1//
            if (y2 + 1 == y1){ //2//
                if (x2  == x1 || x2 +1 == x1 || x2 -1 == x1){
                    return true;
                }
            }
            if(y1 == 6 && y2 + 2 == y1){ // check for first move
                return true;
            }
        } else {//5//
            if (y2 - 1 == y1){
                if (x2  == x1 || x2 +1 == x1 || x2 -1 == x1){
                    return true;
                }
            }
            if(y1 == 1 && y2 - 2 == y1){ // check for first move
                return true;
            }
        }
        return false;
}

class Castle extends Piece {
    public Castle(Boolean assignedColor, int currentPosition) {
        this.color = assignedColor;
        if (assignedColor) { // If white:
            this.head = "CW";
        } else {
            this.head = "CB";
        }

        setPosition(currentPosition);
    }

    public Castle(){} // for queen

    @Override
    Boolean canMove(int newPosition) {
        int x1 = getX(position); // current position
        int y1 = getY(position);
        int x2 = getX(newPosition); // new position
        int y2 = getY(newPosition);

        if (x1 == x2 || y1 == y2) {
            return true;
        }

        return false;
    }

}

class Bishop extends Piece{
    public Bishop(Boolean assignedColor, int currentPosition){
        if (assignedColor) { // If white:
            this.head = "BW";
        } else {
            this.head = "BB";
        }
        setPosition(currentPosition);
    }

    public Bishop(){} // for queen check

	@Override
	Boolean canMove(int newPosition) {
        int x1 = getX(position); // current position
        int y1 = getY(position);
        int x2 = getX(newPosition); // new position
        int y2 = getY(newPosition);

        if (Math.abs(x1 -x2) == Math.abs(y1-y2)){ //1//
            return true;
        }
        return false;
    }
	}
}


class Queen extends Piece{
    
    public Queen(Boolean assignedColor, int currentPosition){
        if (assignedColor) { // If white:
            this.head = "QW";
        } else {
            this.head = "QB";
        }
        setPosition(currentPosition);
    }

	@Override
	Boolean canMove(int newPosition) {
        int x1 = getX(position); // current position
        int y1 = getY(position);
        int x2 = getX(newPosition); // new position
        int y2 = getY(newPosition);

        if (x1 == x2 || y1 == y2) { // check if it moves like Castle
            return true;
        }

        if (Math.abs(x1 - x2) == Math.abs(y1 - y2)){ // check if it moves like bishop
            return true;
        }

        return false;
    }
}

class King extends Piece{
    public King(Boolean assignedColor, int currentPosition){
        if (assignedColor) { // If white:
            this.head = "KW";
        } else {
            this.head = "KB";
        }
        setPosition(currentPosition);
    }

	@Override
	Boolean canMove(int newPosition) {
		int x1 = getX(position); // current position
        int y1 = getY(position);
        int x2 = getX(newPosition); // new position
        int y2 = getY(newPosition);

        if (x2 + 1 == x1  || x2 - 1 == x1  || x2 == x1 ){
            if(y2 + 1 == y1 || y2 - 1 == y1 || y2 == y1){
                return true;
            }
        }
        return false;
	}
}

class Knight extends Piece{
    public Knight(Boolean assignedColor, int currentPosition){
        if (assignedColor) { // If white:
            this.head = "HW";
        } else {
            this.head = "HB";
        }
        setPosition(currentPosition);
    }

	@Override
	Boolean canMove(int newPosition) {
        int x1 = getX(position); // current position
        int y1 = getY(position);
        int x2 = getX(newPosition); // new position
        int y2 = getY(newPosition);

        if (x2-3 == x1  || x2+3 == x1 ){//1//
            if (y2-1 == y1 || y2+1 == y1){//2//
                return true;
            }
        }
        if (y2-3 == y1 || y2+3 == y1){//3//
            if (x2-1 == x1  || x2+1 == x1 ){//4//
                return true;
            }
        }
        return false;
	}
}