package SnakeLadder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PlaySnakeAndLadder {
    public static void main(String []args){
        Dice dice=new Dice(1);
        Player player1=new Player("harsh",1);
        Player player2 = new Player("anuj",2);
        Map<Integer,Integer> snakes=new HashMap<>();
        Map<Integer,Integer> ladders=new HashMap<>();
        snakes.put(25,7);
        snakes.put(99,14);
        ladders.put(8,21);
        ladders.put(10,30);
        Queue<Player>queue= new LinkedList<>();
        queue.offer(player1);
        queue.offer(player2);
        Map<Player,Integer> currentPosition= new HashMap<>();
        currentPosition.put(player1,1);
        currentPosition.put(player2,1);
        int boardSize=100;
        GameBoard gameBoard=new GameBoard(dice,queue,snakes,ladders,currentPosition,boardSize);
        gameBoard.startGame();
    }
}
