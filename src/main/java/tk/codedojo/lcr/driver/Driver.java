package tk.codedojo.lcr.driver;

import tk.codedojo.lcr.beans.*;
import tk.codedojo.lcr.util.Dice;
import tk.codedojo.lcr.util.DiceImpl;

public class Driver {

    private static Player[] players = new Player[2];
    private static Dice dice = new DiceImpl();

    private static void passLeft(int playerIndex){
        if (playerIndex == 0){
            players[1].addCoins(1);
        } else{
            players[0].addCoins(1);
        }
    }

    private static int getNumberOfRolls(int coins){
        if (coins > 3){
            return 3;
        } else{
            return coins;
        }
    }

    private static boolean isLastRoll(){
        return players[0].getCoins() + players[1].getCoins() == 1;
    }

    private static void endGame() {
        DiceValues roll;

        while (true) {
            for (int i = 1; i < players.length + 1; i++) {
                System.out.println("player" + i + " is rolling with the last coin!");
                roll = dice.roll();
                if (roll == DiceValues.CENTER) {
                    System.out.println("Game restarts!");
                    System.exit(0);
                } else if (roll == DiceValues.LEFT) {
                    System.out.println("player" + i + " LEFT");
                    passLeft(i - 1);
                    players[i-1].removeCoins(1);
                } else if (roll == DiceValues.RIGHT) {
                    System.out.println("player" + i + " RIGHT");
                    passLeft(i - 1);
                    players[i-1].removeCoins(1);
                } else {
                    System.out.println("player" + i + " WINS!");
                    System.exit(0);
                }
            }
        }
    }

    private static boolean anyCoinsLeft(){
        return players[0].getCoins() + players[1].getCoins() > 0;
    }

    public static void main(String[] args){
        int coinChange;
        DiceValues roll;

        players[0] = new Player("1");
        players[1] = new Player("2");
        while(true) {
            for (int i = 1; i < players.length+1; i++) {
                coinChange = 0;
                if (!anyCoinsLeft()){
                    System.out.println("Game restarts!");
                    System.exit(0);
                }
                System.out.println("player" + i + " has " + players[i-1].getCoins() + " coins left");
                for (int rolls = 0; rolls < getNumberOfRolls(players[i-1].getCoins()); rolls++) {
                    if(isLastRoll()){
                        endGame();
                    }
                    roll = dice.roll();
                    if (roll == DiceValues.CENTER) {
                        System.out.println("player" + i + " CENTER");
                        coinChange++;
                    } else if (roll == DiceValues.LEFT) {
                        System.out.println("player" + i + " LEFT");
                        passLeft(i-1);
                        coinChange++;
                    } else if (roll == DiceValues.RIGHT) {
                        System.out.println("player" + i + " RIGHT");
                        passLeft(i-1);
                        coinChange++;
                    } else {
                        System.out.println("player" + i + " DOT");
                    }
                }
                players[i-1].removeCoins(coinChange);
            }
        }
    }
}
