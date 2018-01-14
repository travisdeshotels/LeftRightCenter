package tk.codedojo.lcr.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.codedojo.lcr.beans.DiceValues;
import tk.codedojo.lcr.beans.Player;

@Component
public class GameManagerImpl implements GameManager {
    @Autowired
    private Dice dice;
    private Player[] players;

    public GameManagerImpl(){
        players = new Player[2];
    }

    @Override
    public void passLeft(int playerIndex) {
        if (playerIndex == 0){
            players[1].addCoins(1);
        } else{
            players[0].addCoins(1);
        }
    }

    @Override
    public int getNumberOfRolls(int coins) {
        if (coins > 3){
            return 3;
        } else{
            return coins;
        }
    }

    @Override
    public boolean isLastRoll() {
        return players[0].getCoins() + players[1].getCoins() == 1;
    }

    @Override
    public void endGame() {
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

    @Override
    public boolean anyCoinsLeft() {
        return players[0].getCoins() + players[1].getCoins() > 0;
    }

    @Override
    public void runGame() {
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
