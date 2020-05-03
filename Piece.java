abstract class Piece {
    protected Boolean color; // 1 for White, 0 for Black
    protected String head; // Used for printing. Can be removed in the app.
    protected int position; // To keep track of the piece in the board.

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

    // Abstract methods all classes must have:
    abstract Boolean canMove(int newPosition);

}

class EmptyPiece extends Piece {
    public EmptyPiece() {
        this.head = ".";
        this.color = null; // An empty position has a null color.
    }

    @Override
    Boolean canMove(int newPosition) {
        // TODO Auto-generated method stub
        return false;
    }
}

class Pawn extends Piece {
    Boolean FirstStepChecker; // Used to know if the pawn can more twice for the first step.

    public Pawn(Boolean assignedColor, int BoardPosition) {
        FirstStepChecker = true;
        this.color = assignedColor;
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (assignedColor) { // If white:
            this.head = "p";
        } else {
            this.head = "d";
        }
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        setPosition(BoardPosition); // Give location for the Piece.
    }

    @Override
    Boolean canMove(int newPosition) {
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This is the best way to implement arrays into the one integer method used in
        // board. What is happening for the second integer is its turning into a string,
        // getting cut, then turning back into an integer.
        int[] endPoint = { (newPosition / 10) % 10, Integer.parseInt(Integer.toString(newPosition).split("")[1]) };
        int[] startPoint = { (this.position / 10) % 10,
                Integer.parseInt(Integer.toString(this.position).split("")[1]) };
        // TODO Auto-generated method stub
        if (!color) { // 1//
            if (endPoint[1] + 1 == startPoint[1] || (startPoint[1] == 6 && endPoint[1] + 2 == startPoint[1])) { // 2//
                if (endPoint[0] == startPoint[0] || endPoint[0] + 1 == startPoint[0]
                        || endPoint[0] - 1 == startPoint[0]) {// 3//
                    return true;
                }
            }
        } else {// 4//
            if (endPoint[1] - 1 == startPoint[1] || (startPoint[1] == 1 && endPoint[1] - 2 == startPoint[1])) {
                if (endPoint[0] == startPoint[0] || endPoint[0] + 1 == startPoint[0]
                        || endPoint[0] - 1 == startPoint[0]) {
                    return true;
                }
            }
        }
        return false;
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

}

class Castle extends Piece {
    public Castle(Boolean assignedColor, int BoardPosition) {
        this.color = assignedColor;
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (assignedColor) { // If white:
            this.head = "M";
        } else {
            this.head = "W";
        }
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        setPosition(BoardPosition);
    }

    @Override
    Boolean canMove(int newPosition) {
        // TODO Auto-generated method stub
        int[] endPoint = { (newPosition / 10) % 10, Integer.parseInt(Integer.toString(newPosition).split("")[1]) };
        int[] startPoint = { (this.position / 10) % 10,
                Integer.parseInt(Integer.toString(this.position).split("")[1]) };
        if (startPoint[0] == endPoint[0] || startPoint[1] == endPoint[1]) {
            return true;
        }
        return false;
    }

}