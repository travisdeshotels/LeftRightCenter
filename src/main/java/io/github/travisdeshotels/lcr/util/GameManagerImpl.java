package io.github.travisdeshotels.lcr.util;

import io.github.travisdeshotels.lcr.beans.DiceValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class GameManagerImpl implements GameManager {
    @Autowired
    private GameHelper gameHelper;
    private final Dice dice;

    public GameManagerImpl(){
        final Random rand = new Random();
        final List<DiceValues> VALUES = Collections.unmodifiableList(Arrays.asList(DiceValues.values()));
        final int SIZE = VALUES.size();
        dice = () -> VALUES.get(rand.nextInt(SIZE));
    }

    private void endGame() {
        DiceValues roll;

        while (true) {
            for (int i = 1; i < gameHelper.getNumberOfPlayers()+1; i++) {
                System.out.println("player" + i + " is rolling with the last coin!");
                roll = dice.roll();
                if (roll == DiceValues.CENTER) {
                    System.out.println("Game restarts!");
                    System.exit(0);
                } else if (roll == DiceValues.LEFT) {
                    System.out.println("player" + i + " LEFT");
                    gameHelper.passLeft(i - 1);
                    gameHelper.removeCoinsFromPlayer(i-1, 1);
                } else if (roll == DiceValues.RIGHT) {
                    System.out.println("player" + i + " RIGHT");
                    gameHelper.passLeft(i - 1);
                    gameHelper.removeCoinsFromPlayer(i-1, 1);
                } else {
                    System.out.println("player" + i + " WINS!");
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void runGame() {
        while(true) {
            for (int i = 1; i < gameHelper.getNumberOfPlayers()+1; i++) {
                gameHelper.resetCoinChange();
                if (!gameHelper.anyCoinsLeft()){
                    System.out.println("Game restarts!");
                    System.exit(0);
                }
                if(gameHelper.isLastRoll()){
                    endGame();
                }
                System.out.println("player" + i + " has " + gameHelper.getPlayerCoins(i-1) + " coins left");
                for (int rolls = 0; rolls < gameHelper.getNumberOfRolls(gameHelper.getPlayerCoins(i-1)); rolls++) {
                    System.out.println(gameHelper.processRoll(dice.roll(), i));
                }
                gameHelper.removeCoinsFromPlayer(i-1);
            }
        }
    }
}
