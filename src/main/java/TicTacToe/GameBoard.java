package TicTacToe;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class GameBoard {
    int boardSize;
    char board[][];
    Queue<Players> turn;
    int[] rowSum;
    int[] colSum;
    int leftDiagonalSum;
    int rightDiagonalSum;

    public GameBoard(int boardSize, List<Players> playersList) {
        this.boardSize = boardSize;
        board = new char[boardSize][boardSize];
        initializeBoard(boardSize);

        // Initialize turn queue with the players
        turn = new LinkedList<>(playersList);

        // Initialize sums for rows, columns, and diagonals
        rowSum = new int[boardSize];
        colSum = new int[boardSize];
        leftDiagonalSum = 0;
        rightDiagonalSum = 0;
    }

    private void initializeBoard(int boardSize) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '*';
            }
        }
    }

    public void playGame() {
        int moves = 0; // Count of moves made
        while (true) {
            Players currentPlayer = turn.poll(); // Get the next player
            System.out.println("Player " + currentPlayer.name + "'s turn. Enter your move (row and column): ");

            // Get user input and place their symbol
            getUserInput(currentPlayer);
            printBoard();
            moves++;

            // Check for winner
            if (checkWinner(currentPlayer)) {
                System.out.println("Player " + currentPlayer.name + " wins!");
                break;
            }

            // Check for draw
            if (moves == boardSize * boardSize) {
                System.out.println("Match Draw!");
                break;
            }

            turn.offer(currentPlayer); // Put the player back in the queue
        }
    }

    private boolean checkWinner(Players p) {
        // Check row, column, and diagonals for a win
        for (int i = 0; i < boardSize; i++) {
            // Check if any row or column has all the same symbol
            if (isRowWin(i, p.symbol) || isColWin(i, p.symbol)) {
                return true;
            }
        }

        // Check diagonals
        if (isLeftDiagonalWin(p.symbol) || isRightDiagonalWin(p.symbol)) {
            return true;
        }

        return false;
    }

    // Helper methods to check for wins in rows, columns, and diagonals
    private boolean isRowWin(int row, char symbol) {
        for (int i = 0; i < boardSize; i++) {
            if (board[row][i] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean isColWin(int col, char symbol) {
        for (int i = 0; i < boardSize; i++) {
            if (board[i][col] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean isLeftDiagonalWin(char symbol) {
        for (int i = 0; i < boardSize; i++) {
            if (board[i][i] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean isRightDiagonalWin(char symbol) {
        for (int i = 0; i < boardSize; i++) {
            if (board[i][boardSize - i - 1] != symbol) {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void getUserInput(Players player) {
        Scanner sc = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.print("Enter row and column (1-" + boardSize + "): ");
            row = sc.nextInt();
            col = sc.nextInt();

            // Check if the cell is within bounds and unoccupied
            if (row >= 1 && row <= boardSize && col >= 1 && col <= boardSize && board[row - 1][col - 1] == '*') {
                break;
            } else {
                System.out.println("Invalid input or cell already occupied. Try again.");
            }
        }

        // Place the player's symbol on the board
        board[row - 1][col - 1] = player.symbol;
    }
}
