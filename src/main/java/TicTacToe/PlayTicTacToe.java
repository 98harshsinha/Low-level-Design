package TicTacToe;

import java.util.List;

public class PlayTicTacToe {
    public static void main(String[]args){
        Players player1 = new Players("Player 1", 'X');
        Players player2 = new Players("Player 2", 'O');

        List<Players> playersList = List.of(player1, player2); // Add players to a list

        GameBoard gameBoard = new GameBoard(3, playersList); // Initialize a 3x3 board
        gameBoard.playGame();
    }
}
