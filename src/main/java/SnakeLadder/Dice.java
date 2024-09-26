package SnakeLadder;

import java.util.Random;

public class Dice {
    private int noOfDices;

     Dice(int noOfDices){
        this.noOfDices=noOfDices;
    }
    int rollDice(){
        Random random = new Random();
        int min = 1 * noOfDices;
        int max = 6 * noOfDices;
        return random.nextInt(max - min + 1) + min;
    }
}
