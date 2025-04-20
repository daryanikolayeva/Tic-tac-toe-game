
// Controls the overall flow of the game
// This includes input, checking games states
// and initialization of the board itself. 
//Nikolayeva Darya

import java.util.Scanner;
import java.util.Random;

public class GameManager {

    // Shorthand for players
    public static final char playerX = 'X';
    public static final char playerO = 'O';

    // The board object itself. Holds game state.
    private static TicTacToe board = new TicTacToe();
    private static char playersTurn = playerX;

    public static void main(String[] args) {

        // Create the Game Manager object itself.
        GameManager man = new GameManager();

        Random random = new Random();
        boolean chosenPlayer = random.nextBoolean();// randomly takes true or false with equal chances
        if (chosenPlayer) {
            man.setPlayersTurn(playerX);// either assigns playerX
        } else {
            man.setPlayersTurn(playerO);// or playerO
        }

        // start up game
        man.initGame();
        try {// catches exception when tile is out of range
            man.nextTurn();
        } catch (Exception e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }
        System.out.println(board.toString());

        System.out.println(board.toString());

        // Game loop (until game is over).
        while (!man.isGameOver()) {
            try {// catches exception when tile is out of range
                man.nextTurn();
            } catch (Exception e) {
                System.out.println("An exception occurred: " + e.getMessage());
            }
            System.out.println(board.toString());
        }

        // Quit.
        if (man.isGameOver()) {
            char winner = ' ';
            if (playersTurn == 'X') {// changes value of playerTurn, becuase oterwise gives player who just got turn
                winner = 'O';// previous player is the one who won
            } else if (playersTurn == 'O') {// same here
                winner = 'X';
            }
            man.playerWon(winner);
            System.out.println("Thanks for playing");
            System.exit(0);
        }
    }

    public void setPlayersTurn(char turn) {// setter for playersTurn
        playersTurn = turn;
    }

    public char getPlayersTurn() {// returns playersTurn, cause it is private
        return playersTurn;
    }

    // Initialize Game
    public void defaultInitGame() {// first version with default size 3x3
        board = new TicTacToe(3, 3);// if so, creates new board with this size
        playersTurn = 'X';// user X goes first;2a
    }

    public void initGame() {// version where user is asked for size and board of this size created
        Scanner input = new Scanner(System.in);// scanner for user input
        System.out.println("Enter size of board");
        int size = input.nextInt();// input is size of board
        if (size >= 2 && size <= 100) {// checks if size is valid
            board = new TicTacToe(size, size);// if so, creates new board with this size
        } else {
            System.out.println("Invalid size");// if not prints- invalid size
        }

        playersTurn = 'X';// user X goes first;2a
    }

    public void nextPlayer() {// phase 2b
        if (playersTurn == 'X') {// if user x, chage to o
            playersTurn = 'O';
        } else if (playersTurn == 'O') {// if user o change to x
            playersTurn = 'X';
        }
    }

    private void nextTurn() throws Exception {// has exception to warn when entered tile is outside of range
        // not using just warning message, because tile will give exception before
        // getting to message, as it will be out of bounds and will stop programm
        System.out.println("Current turn is player " + playersTurn);// 2i, syas who's current player
        Scanner input = new Scanner(System.in);// scanner to take user input
        System.out.println("Enter a row for your turn");// asks for row;2ii
        int row = input.nextInt();// input is now row
        System.out.println("Enter a collumn for your turn");// asks for collumns;2ii
        int collumn = input.nextInt();// input is now collumn
        if (row < 0 || row >= board.getMatrix().length || collumn < 0 || collumn >= board.getMatrix()[0].length) {
            throw new Exception("Tile is out of range or occupied");// throws exception if tile is out of range

        }
        if (board.isEmpty(row, collumn)) {// first checks if given tile is empty by method, then chhecks if it is in
                                          // range of matrix used;2iii
            if (playersTurn == 'X') {// if its user with X;2iv;
                board.getMatrix()[row][collumn] = 'X';// puts X at given tile
            } else if (playersTurn == 'O') {// if its user with O;2iv;
                board.getMatrix()[row][collumn] = 'O';// puts O at given tile
            }
            nextPlayer();// changes players turn if user could put its symbol on board
        } else {
            System.out.println("Tile is out of range or occupied");// if tile occupied or out of range, payers turn
                                                                   // doesnt chage
            if (playersTurn == 'X') {// if user was X
                playersTurn = 'X';// not update to next players turn
            } else if (playersTurn == 'O') {// if user was o
                playersTurn = 'O';// not update to next players turn
            }

        }
        System.out.println("Current turn is player " + playersTurn);// printing whos turn now;2v
    }

    // Is the game over?
    private boolean isGameOver() {
        return board.gameOver();
    }

    // A player has won, output a message and quit the game.
    private void playerWon(char playerThatWon) {
        System.out.println("Player " + playerThatWon + " won the game!");
        System.exit(0);
    }
}