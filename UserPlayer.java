/**
 * The `UserPlayer` class represents a player controlled by a human user in the MiniCheckers game.
 * It extends the abstract class `Player`.
 * Names: Yazan Alshoroogi
 * netID: yalshoro
 * G#: 01395561
 * Lecture section: 001
 * Lab section: 202
 */

import java.util.*;
public class UserPlayer extends Player {

    // Instance variables
    private String name;
    private Scanner input;

    /**
     * Constructor for the `UserPlayer` class.
     *
     * @param input The Scanner object used for user input.
     * @param name  The name of the user.
     */
    public UserPlayer(Scanner input, String name) {
        this.name = name;
        this.input = input;
    }

    /**
     * Overrides the toString method to provide a string representation of the user's name.
     *
     * @return The name of the user.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Allows the user to choose a move from the list of possible moves on the current game board.
     * Displays the current board, available moves, and prompts the user for input.
     *
     * @param currentBoard The current state of the MiniCheckers game.
     * @return The MiniCheckers instance representing the chosen move.
     */
    @Override
    public MiniCheckers chooseMove(MiniCheckers currentBoard) {
        // Print the current board
        System.out.println("Current Board:\n" + currentBoard.toString());

        // Get possible moves
        ArrayList<MiniCheckers> possibleMoves = currentBoard.possibleMoves(this);

        // Print available options
        System.out.println("Possible Moves:");
        for (int i = 0; i < possibleMoves.size(); i++) {
            System.out.println(i + ": \n" + possibleMoves.get(i).toString());
        }

        // Ask the user to select a move
        System.out.print("Select a move (enter the corresponding index): ");
        while(!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid index.");
            System.out.print("Select a move (enter the corresponding index): ");
            input.next();  // Consume the invalid input
        }

        int userChoice = input.nextInt();

        while (userChoice >= possibleMoves.size() || userChoice < 0) {
            System.out.println("Invalid input. Please enter a valid index.");
            System.out.print("Select a move (enter the corresponding index): ");
            while(!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid index.");
                System.out.print("Select a move (enter the corresponding index): ");
                input.next();  // Consume the invalid input
            }
            userChoice = input.nextInt();
        }

        // Return the selected move
        return possibleMoves.get(userChoice);
    }
}
