/**
 * The abstract base class for MiniCheckers players.
 * Subclasses must implement the chooseMove method to determine the next move in the game.
 * Additionally, the boardValue method is provided to evaluate the current state of the game board.
 * Names: Yazan Alshoroogi
 * netID: yalshoro
 * G#: 01395561
 * Lecture section: 001
 * Lab section: 202
 */

public abstract class Player {

    /**
     * Abstract method that must be implemented by subclasses to determine the next move in the game.
     *
     * @param miniCheckers The current state of the MiniCheckers game.
     * @return A MiniCheckers instance representing the chosen move.
     */
    public abstract MiniCheckers chooseMove(MiniCheckers miniCheckers);

    /**
     * Evaluates the current state of the game board.
     *
     * @param miniCheckers The current state of the MiniCheckers game.
     * @return 1.0 if the player has won, -1.0 if the player has lost, and 0.0 otherwise.
     */
    public double boardValue(MiniCheckers miniCheckers) {
        if (miniCheckers.checkWin(this)) {
            return 1.0;
        } else if (miniCheckers.checkLose(this)) {
            return -1.0;
        } else {
            return 0.0;
        }
    }
}
