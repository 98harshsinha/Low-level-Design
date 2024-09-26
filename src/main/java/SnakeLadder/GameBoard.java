package SnakeLadder;

import java.util.Map;
import java.util.Queue;

public class GameBoard {
    private Dice dice;
    private Queue<Player> queue;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;
    private Map<Player, Integer> currentPosition;
    private int boardSize;

    GameBoard(Dice dice, Queue<Player> queue, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, Map<Player, Integer> currentPosition, int boardSize) {
        this.dice = dice;
        this.queue = queue;
        this.snakes = snakes;
        this.ladders = ladders;
        this.currentPosition = currentPosition;
        this.boardSize = boardSize;
    }

    void startGame() {
        boolean gameOver = false;

        while (queue.size() > 1 && !gameOver) {
            Player player = queue.poll();
            int curPos = currentPosition.get(player);
            int diceValue = dice.rollDice();
            int nextCell = curPos + diceValue;

            if (nextCell > boardSize) {
                queue.offer(player);  // No movement, player's turn ends
            } else if (nextCell == boardSize) {
                System.out.println(player.getName() + " has won the game!");
                gameOver = true;  // End game after winning
            } else {
                // Check if player hits a snake or ladder
                if (snakes.containsKey(nextCell)) {
                    int snakeTail = snakes.get(nextCell);
                    System.out.println(player.getName() + " got bitten by a snake! Moving down to " + snakeTail);
                    currentPosition.put(player, snakeTail);
                } else if (ladders.containsKey(nextCell)) {
                    int ladderTop = ladders.get(nextCell);
                    System.out.println(player.getName() + " climbed a ladder! Moving up to " + ladderTop);
                    currentPosition.put(player, ladderTop);
                } else {
                    currentPosition.put(player, nextCell);  // Normal move
                }
                queue.offer(player);  // Player's turn ends, re-add them to the queue
            }
        }
    }
}
