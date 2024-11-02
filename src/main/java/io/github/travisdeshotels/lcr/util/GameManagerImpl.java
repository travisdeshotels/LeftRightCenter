package io.github.travisdeshotels.lcr.util;

import io.github.travisdeshotels.lcr.beans.DiceValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class GameManagerImpl implements GameManager {
    @Autowired
    private GameHelper gameHelper;
    private final Dice dice;
    private GameOutput out;
    BufferedWriter writer;

    public GameManagerImpl(){
        final Random rand = new Random();
        final List<DiceValues> VALUES = Collections.unmodifiableList(Arrays.asList(DiceValues.values()));
        final int SIZE = VALUES.size();
        dice = () -> VALUES.get(rand.nextInt(SIZE));
        if (System.getenv("FILENAME") == null){
            out = System.out::println;
        } else{
            setupFileOutput();
        }
    }

    private void setupFileOutput(){
        try {
            writer = new BufferedWriter(new FileWriter(System.getenv("FILENAME")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out = str -> {
            try {
                System.out.println(str);
                writer.write(str + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private void closeWriter(){
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void endGame() {
        DiceValues roll;

        while (true) {
            for (int i = 1; i < gameHelper.getNumberOfPlayers()+1; i++) {
                out.print("player" + i + " is rolling with the last coin!");
                roll = dice.roll();
                if (roll == DiceValues.CENTER) {
                    out.print("Game restarts!");
                    System.exit(0);
                } else if (roll == DiceValues.LEFT) {
                    out.print("player" + i + " LEFT");
                    gameHelper.passLeft(i - 1);
                    gameHelper.removeCoinsFromPlayer(i-1, 1);
                } else if (roll == DiceValues.RIGHT) {
                    out.print("player" + i + " RIGHT");
                    gameHelper.passLeft(i - 1);
                    gameHelper.removeCoinsFromPlayer(i-1, 1);
                } else {
                    out.print("player" + i + " WINS!");
                    closeWriter();
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
                    out.print("Game restarts!");
                    System.exit(0);
                }
                if(gameHelper.isLastRoll()){
                    endGame();
                }
                out.print("player" + i + " has " + gameHelper.getPlayerCoins(i-1) + " coins left");
                for (int rolls = 0; rolls < gameHelper.getNumberOfRolls(gameHelper.getPlayerCoins(i-1)); rolls++) {
                    out.print(gameHelper.processRoll(dice.roll(), i));
                }
                gameHelper.removeCoinsFromPlayer(i-1);
            }
        }
    }
}
