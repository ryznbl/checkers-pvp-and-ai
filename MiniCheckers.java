/**
 * Represents a simplified game of checkers on a 5x5 board.
 * Players can move and capture checkers diagonally.
 * Checkers can be promoted to kings upon reaching the opposite end of the board.
 * This class provides utility methods to interact with the game board.
 * Names: Yazan Alshoroogi
 * netID: yalshoro
 * G#: 01395561
 * Lecture section: 001
 * Lab section: 202
 */

import java.util.ArrayList;

public class MiniCheckers {

    // Instance variables
    private char[][] board;
    private Player red;
    private Player black;

    /**
     * Constructs a MiniCheckers instance with the given players.
     *
     * @param red   The player controlling the red checkers.
     * @param black The player controlling the black checkers.
     */
    public MiniCheckers(Player red, Player black) {
        this.red = red;
        this.black = black;

        board = new char[][] {
                {'r', '.', 'r', '.', 'r'},
                {'.', '_', '.', '_', '.'},
                {'_', '.', '_', '.', '_'},
                {'.', '_', '.', '_', '.'},
                {'b', '.', 'b', '.', 'b'}
        };
    }

    /**
     * Gets the current game board.
     *
     * @return The 2D char array representing the game board.
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Sets the game board to the specified 2D char array.
     *
     * @param board The new game board to set.
     */
    public void setBoard(char[][] board) {
        this.board = board;
    }

    /**
     * Gets the player controlling the red checkers.
     *
     * @return The player controlling the red checkers.
     */
    public Player getRed() {
        return red;
    }

    /**
     * Sets the player controlling the red checkers.
     *
     * @param red The new player controlling the red checkers.
     */
    public void setRed(Player red) {
        this.red = red;
    }

    /**
     * Gets the player controlling the black checkers.
     *
     * @return The player controlling the black checkers.
     */
    public Player getBlack() {
        return black;
    }

    /**
     * Sets the player controlling the black checkers.
     *
     * @param black The new player controlling the black checkers.
     */
    public void setBlack(Player black) {
        this.black = black;
    }

    /**
     * Returns the number of checkers on the board that match the specified color.
     *
     * @param color The color of the checkers to count ('r' for red, 'b' for black).
     * @return The number of checkers on the board that match the specified color.
     */
    public int countChecker(char color) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == color) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Checks if the given player has won the game.
     *
     * @param player The player to check for victory.
     * @return True if the player has won, false otherwise.
     */
    public boolean checkWin(Player player) { //this needs to check for capital letters as well
        char x;
        char y;
        if (player == red) {
            x = 'b';
            y = 'B';
        } else {
            x = 'r';
            y = 'R';
        }
        return (countChecker(x) == 0 && countChecker(y) == 0);
    }

    /**
     * Checks if the opponent of the given player has won the game.
     *
     * @param player The player whose opponent to check for victory.
     * @return True if the opponent has won, false otherwise.
     */
    public boolean checkLose(Player player) {
        char x;
        char y;
        if (player == red) {
            x = 'r';
            y = 'R';
        } else {
            x = 'b';
            y = 'B';
        }
        return countChecker(x) == 0 && countChecker(y) == 0;
    }

    /**
     * Returns a string representation of the current game board.
     *
     * @return A string representation of the game board.
     */
    public String toString() {
        String res = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                res += board[i][j];
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Creates a deep clone of the current MiniCheckers instance.
     *
     * @return A new MiniCheckers instance that is a deep copy of the current game state.
     */
    private MiniCheckers makeClone() {
        /* provided code. You may call this method, but you should NOT modify it */
        MiniCheckers newMiniCheckers = new MiniCheckers(this.red, this.black);
        char[][] newBoard = newMiniCheckers.getBoard();
        char[][] curBoard = this.getBoard();
        for (int i = 0; i < curBoard.length; i++) {
            for (int j = 0; j < curBoard[i].length; j++) {
                newBoard[i][j] = curBoard[i][j];
            }
        }
        newMiniCheckers.setBoard(newBoard);
        return newMiniCheckers;
    }

    /**
     * Creates a new MiniCheckers instance with a piece moved from the specified start to end coordinates.
     *
     * @param startRow The starting row of the piece.
     * @param startCol The starting column of the piece.
     * @param endRow   The ending row where the piece will be moved.
     * @param endCol   The ending column where the piece will be moved.
     * @return A new MiniCheckers instance with the specified move applied.
     */
    private MiniCheckers movePiece(int startRow, int startCol, int endRow, int endCol){
        /* provided code. You may call this method, but you should NOT modify it */
        MiniCheckers move = this.makeClone();
        char[][] newBoard = move.getBoard();
        char tmpPiece = newBoard[startRow][startCol];
        newBoard[startRow][startCol] = '_';
        newBoard[endRow][endCol] = tmpPiece;
        if((tmpPiece=='r' && endRow==newBoard.length-1)||(tmpPiece=='b'&&endRow==0)){
            newBoard[endRow][endCol] = Character.toUpperCase(newBoard[endRow][endCol]);
        }
        return move;
    }

    /**
     * Removes a piece from the specified coordinates on the given board.
     *
     * @param board The game board.
     * @param i     The row index of the piece to remove.
     * @param j     The column index of the piece to remove.
     */
    private static void removePiece(char[][] board, int i, int j){
        /* provided code. You may call this method, but you should NOT modify it */
        board[i][j] = '_';
    }

    /**
     * Checks if the specified indices are valid for the given game board.
     *
     * @param board The game board.
     * @param i     The row index to check.
     * @param j     The column index to check.
     * @return True if the indices are valid; otherwise, false.
     */
    private static boolean validIndex(char[][] board, int i, int j){
        /* provided code. You may call this method, but you should NOT modify it */
        if(i<0 || j<0 || i>=board.length || j>=board[0].length) return false;
        return true;
    }

    /**
     * Checks if the red player can move a checker from the starting position to the ending position.
     *
     * @param startRow    The starting row of the checker.
     * @param startCol The starting column of the checker.
     * @param endRow      The ending row for the move.
     * @param endCol  The ending column for the move.
     * @return True if the move is valid for the red player, false otherwise.
     */
    private static boolean redCanMoveHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        // Check if indices are out of bounds
        if (!validIndex(board, startRow, startCol) || !validIndex(board, endRow, endCol)) {
            return false;
        }

        // Check if the move is exactly 1 square diagonally forward
        if (Math.abs(startRow - endRow) != 1 || Math.abs(startCol - endCol) != 1) {
            return false;
        }

        // Check if there is a red checker at the starting position
        if (board[startRow][startCol] != 'r' && board[startRow][startCol] != 'R') {
            return false;
        }

        // Check if the checker at the starting position is not a king and is moving backward
        if (board[startRow][startCol] == 'r' && endRow <= startRow) {
            return false;
        }

        // Check if the ending position is an unoccupied space
        return board[endRow][endCol] == '_';
    }

    /**
     * Checks if a red checker can perform a jump move from the specified start to end coordinates.
     *
     * @param board    The game board.
     * @param startRow The starting row of the piece.
     * @param startCol The starting column of the piece.
     * @param endRow   The ending row where the piece will be moved.
     * @param endCol   The ending column where the piece will be moved.
     * @return True if a red checker can jump from the start to end coordinates; otherwise, false.
     */
    private static boolean redCanJumpHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        /* provided code. You may call this method, but you should NOT modify it */
        if(!validIndex(board, startRow, startCol) || !validIndex(board,endRow,endCol)) return false;
        if(Math.abs(startRow-endRow)!=2 || Math.abs(startCol-endCol)!=2) return false;
        if(board[startRow][startCol] == 'r'){
            if(endRow != startRow+2) return false;
            if(board[endRow][endCol] != '_') return false;
            if( (endCol == startCol+2 && (board[startRow+1][startCol+1] == 'b' || board[startRow+1][startCol+1] == 'B')) ||
                (endCol == startCol-2 && (board[startRow+1][startCol-1] == 'b' || board[startRow+1][startCol-1] == 'B'))){
                return true;
            } else {
                return false;
            }
        } else if(board[startRow][startCol] == 'R'){
            if(board[endRow][endCol] != '_') return false;
            if(endRow > startRow && endCol > startCol){
                //down-right
                if(board[startRow+1][startCol+1]=='b' || board[startRow+1][startCol+1]=='B') return true;
                else return false;
            } else if(endRow < startRow && endCol > startCol){
                //up-right
                if(board[startRow-1][startCol+1]=='b' || board[startRow-1][startCol+1]=='B') return true;
                else return false;
            } else if(endRow > startRow && endCol < startCol){
                //down-left
                if(board[startRow+1][startCol-1]=='b' || board[startRow+1][startCol-1]=='B') return true;
                else return false;
            } else if(endRow < startRow && endCol < startCol){
                //up-left
                if(board[startRow-1][startCol-1]=='b' || board[startRow-1][startCol-1]=='B') return true;
                else return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if a black checker can perform a regular move from the specified start to end coordinates.
     *
     * @param board    The game board.
     * @param startRow The starting row of the piece.
     * @param startCol The starting column of the piece.
     * @param endRow   The ending row where the piece will be moved.
     * @param endCol   The ending column where the piece will be moved.
     * @return True if a black checker can move from the start to end coordinates; otherwise, false.
     */
    private static boolean blackCanMoveHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        // Check if indices are out of bounds
        if (!validIndex(board, startRow, startCol) || !validIndex(board, endRow, endCol)) {
            return false;
        }

        // Check if the move is exactly 1 square diagonally forward
        if (Math.abs(startRow - endRow) != 1 || Math.abs(startCol - endCol) != 1) {
            return false;
        }

        // Check if there is a black checker at the starting position
        if (board[startRow][startCol] != 'b' && board[startRow][startCol] != 'B') {
            return false;
        }

        // Check if the checker at the starting position is not a king and is moving backward
        if (board[startRow][startCol] == 'b' && endRow >= startRow) {
            return false;
        }

        // Check if the ending position is an unoccupied space
        return board[endRow][endCol] == '_';
    }

    /**
     * Checks if a black checker can perform a jump move from the specified start to end coordinates.
     *
     * @param board    The game board.
     * @param startRow The starting row of the piece.
     * @param startCol The starting column of the piece.
     * @param endRow   The ending row where the piece will be moved.
     * @param endCol   The ending column where the piece will be moved.
     * @return True if a black checker can jump from the start to end coordinates; otherwise, false.
     */
    private static boolean blackCanJumpHere(char[][] board, int startRow, int startCol, int endRow, int endCol){
        /* provided code. You may call this method, but you should NOT modify it */
        if(!validIndex(board, startRow, startCol) || !validIndex(board,endRow,endCol)) return false;
        if(Math.abs(startRow-endRow)!=2 || Math.abs(startCol-endCol)!=2) return false;
        if(board[startRow][startCol] == 'b'){
            if(endRow != startRow-2) return false;
            if(board[endRow][endCol] != '_') return false;
            if( (endCol == startCol+2 && (board[startRow-1][startCol+1] == 'r' || board[startRow-1][startCol+1] == 'R')) ||
                (endCol == startCol-2 && (board[startRow-1][startCol-1] == 'r' || board[startRow-1][startCol-1] == 'R'))){
                return true;
            } else {
                return false;
            }
        } else if(board[startRow][startCol] == 'B'){
            if(board[endRow][endCol] != '_') return false;
            if(endRow > startRow && endCol > startCol){
                //down-right
                if(board[startRow+1][startCol+1]=='r' || board[startRow+1][startCol+1]=='R') return true;
                else return false;
            } else if(endRow < startRow && endCol > startCol){
                //up-right
                if(board[startRow-1][startCol+1]=='r' || board[startRow-1][startCol+1]=='R') return true;
                else return false;
            } else if(endRow > startRow && endCol < startCol){
                //down-left
                if(board[startRow+1][startCol-1]=='r' || board[startRow+1][startCol-1]=='R') return true;
                else return false;
            } else if(endRow < startRow && endCol < startCol){
                //up-left
                if(board[startRow-1][startCol-1]=='r' || board[startRow-1][startCol-1]=='R') return true;
                else return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * Generates a list of possible game states resulting from valid moves for the specified player.
     *
     * @param player The player for whom to generate possible moves.
     * @return An ArrayList of MiniCheckers instances representing possible game states.
     */
    public ArrayList<MiniCheckers> possibleMoves(Player player) {
        /* provided code. You may call this method, but you should NOT modify it */
        char[][] curBoard = this.getBoard();
        ArrayList<MiniCheckers> rv = new ArrayList<MiniCheckers>();
        if(player == this.red){
            boolean couldJump = false;
            for(int i=0;i<curBoard.length;i++){
                for(int j=0;j<curBoard[i].length;j++){
                    if(board[i][j]=='r' || board[i][j] =='R'){
                        if(redCanJumpHere(board,i,j,i-2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j-2);
                            removePiece(newBoard.board,i-1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(redCanJumpHere(board,i,j,i-2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j+2);
                            removePiece(newBoard.board,i-1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(redCanJumpHere(board,i,j,i+2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j-2);
                            removePiece(newBoard.board,i+1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(redCanJumpHere(board,i,j,i+2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j+2);
                            removePiece(newBoard.board,i+1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                    }
                }
            }
            if(!couldJump){
                for(int i=0;i<curBoard.length;i++){
                    for(int j=0;j<curBoard[i].length;j++){
                        if(board[i][j]=='r' || board[i][j]=='R'){                            
                            if(redCanMoveHere(board,i,j,i-1,j-1)){
                                rv.add(movePiece(i,j,i-1,j-1));
                            }
                            if(redCanMoveHere(board,i,j,i-1,j+1)){
                                rv.add(movePiece(i,j,i-1,j+1));
                            }
                            if(redCanMoveHere(board,i,j,i+1,j-1)){
                                rv.add(movePiece(i,j,i+1,j-1));
                            }
                            if(redCanMoveHere(board,i,j,i+1,j+1)){
                                rv.add(movePiece(i,j,i+1,j+1));
                            }
                        }
                    }
                }
            }
        } else if(player==this.black){
            boolean couldJump = false;
            //check for jumps first
            for(int i=0;i<curBoard.length;i++){
                for(int j=0;j<curBoard[i].length;j++){
                    if(board[i][j]=='b' || board[i][j] =='B'){
                        if(blackCanJumpHere(board,i,j,i-2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j-2);
                            removePiece(newBoard.board,i-1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(blackCanJumpHere(board,i,j,i-2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i-2,j+2);
                            removePiece(newBoard.board,i-1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(blackCanJumpHere(board,i,j,i+2,j-2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j-2);
                            removePiece(newBoard.board,i+1,j-1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if(blackCanJumpHere(board,i,j,i+2,j+2)){
                            MiniCheckers newBoard = movePiece(i,j,i+2,j+2);
                            removePiece(newBoard.board,i+1,j+1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                    }
                }
            }
            if(!couldJump){
                for(int i=0;i<curBoard.length;i++){
                    for(int j=0;j<curBoard[i].length;j++){
                        if(board[i][j]=='b' || board[i][j]=='B'){
                            if(blackCanMoveHere(board,i,j,i-1,j-1)){
                                rv.add(movePiece(i,j,i-1,j-1));
                            }
                            if(blackCanMoveHere(board,i,j,i-1,j+1)){
                                rv.add(movePiece(i,j,i-1,j+1));
                            }
                            if(blackCanMoveHere(board,i,j,i+1,j-1)){
                                rv.add(movePiece(i,j,i+1,j-1));
                            }
                            if(blackCanMoveHere(board,i,j,i+1,j+1)){
                                rv.add(movePiece(i,j,i+1,j+1));
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Unrecognized player?!");
        }
        return rv;
    }
}