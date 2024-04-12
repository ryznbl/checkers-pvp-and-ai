/**
 * The `AIPlayer` class represents an artificial intelligence player in the MiniCheckers game.
 * It extends the abstract class `Player` and implements methods for choosing moves and evaluating board positions.
 * Names: Yazan Alshoroogi
 * netID: yalshoro
 * G#: 01395561
 * Lecture section: 001
 * Lab section: 202
 */

import java.util.*;
public class AIPlayer extends Player {

    // Instance variables
    private String name;
    private Player opponent;
    private static final int INITIAL_DEPTH = 10; //So that it is easier to change the depth of the AI

    /**
     * Constructor for the `AIPlayer` class.
     *
     * @param name     The name of the AI player.
     * @param opponent The opponent player (human or AI) for the AI player.
     */
    public AIPlayer(String name, Player opponent) {
        this.name = name;
        this.opponent = opponent;
    }

    /**
     * Retrieves the opponent player.
     *
     * @return The opponent player.
     */
    public Player getOpponent() {
        return opponent;
    }

    /**
     * Sets the opponent player.
     *
     * @param opponent The opponent player to be set.
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Overrides the toString method to provide a string representation of the AI player.
     *
     * @return A string containing the name of the AI player with an indication that it is controlled by AI.
     */
    @Override
    public String toString() {
        return name + " (AI)";
    }

    /**
     * Recursive function for calculating the minimum value during the AI's decision-making process.
     *
     * @param game  The current state of the MiniCheckers game.
     * @param depth The depth of the recursive search.
     * @return The calculated minimum value.
     */
    private double minValue(MiniCheckers game, int depth) {
        // Base cases: check for win, lose, or reach maximum depth
        if (game.checkWin(this)) {
            return 10.0;
        } else if (game.checkLose(this)) {
            return -10.0;
        } else if (depth < 1) {
            return calculateRelativeStrength(game);
        }

        // Initialize minValue to positive infinity
        double minValue = Double.POSITIVE_INFINITY;
        ArrayList<MiniCheckers> possibleMoves = game.possibleMoves(opponent);

        // If no possible moves, return 0.0 (draw)
        if (possibleMoves.isEmpty()){
            return 0.0;
        }

        // Iterate over possible moves and calculate the minimum value
        for (MiniCheckers move : possibleMoves) {
            double value = maxValue(move, depth - 1);
            minValue = Math.min(minValue, value);
        }

        return minValue;
    }

    /**
     * Recursive function for calculating the maximum value during the AI's decision-making process.
     *
     * @param game  The current state of the MiniCheckers game.
     * @param depth The depth of the recursive search.
     * @return The calculated maximum value.
     */
    private double maxValue(MiniCheckers game, int depth) {
        // Base cases: check for win, lose, or reach maximum depth
        if (game.checkWin(this)) {
            return 10.0;
        } else if (game.checkLose(this)) {
            return -10.0;
        } else if (depth < 1) {
            return calculateRelativeStrength(game);
        }

        // Initialize maxValue to negative infinity
        double maxValue = Double.NEGATIVE_INFINITY;
        ArrayList<MiniCheckers> possibleMoves = game.possibleMoves(this);

        // If no possible moves, return 0.0 (draw)
        if (possibleMoves.isEmpty()){
            return 0.0;
        }

        // Iterate over possible moves and calculate the maximum value
        for (MiniCheckers move : possibleMoves) {
            double value = minValue(move, depth - 1);
            maxValue = Math.max(maxValue, value);
        }

        return maxValue;
    }

    /**
     * Calculates the relative strength of the AI player on the given game board.
     *
     * @param game The current state of the MiniCheckers game.
     * @return The calculated relative strength.
     */
    private double calculateRelativeStrength(MiniCheckers game) {
        char playerColor = (this == game.getRed()) ? 'r' : 'b';
        char opponentColor = (playerColor == 'r') ? 'b' : 'r';

        int numMyCheckers = game.countChecker(playerColor);
        int numMyKings = game.countChecker(Character.toUpperCase(playerColor));
        int numOppCheckers = game.countChecker(opponentColor);
        int numOppKings = game.countChecker(Character.toUpperCase(opponentColor));

        return (numMyCheckers + 3 * numMyKings) - (numOppCheckers + 3 * numOppKings);
    }

    /**
     * Overrides the `chooseMove` method to make the AI player choose the best move based on the MiniMax algorithm.
     *
     * @param game The current state of the MiniCheckers game.
     * @return The MiniCheckers instance representing the chosen move.
     */
    @Override
    public MiniCheckers chooseMove(MiniCheckers game) {
        int depth = INITIAL_DEPTH; // Initial depth value
        ArrayList<MiniCheckers> possibleMoves = game.possibleMoves(this);
        double maxValue = Double.NEGATIVE_INFINITY;
        MiniCheckers bestMove = null;

        // Iterate over possible moves and choose the one with the maximum value
        for (MiniCheckers move : possibleMoves) {
            double value = minValue(move, depth - 1);
            if (value > maxValue) {
                maxValue = value;
                bestMove = move;
            }
        }

        return bestMove;
    }

    /**
     * Overrides the `boardValue` method to calculate the board value using the maxValue function.
     *
     * @param board The current state of the MiniCheckers game.
     * @return The calculated board value.
     */
    @Override
    public double boardValue(MiniCheckers board) {
        return maxValue(board, INITIAL_DEPTH); // Use maxValue to compute board value
    }
}