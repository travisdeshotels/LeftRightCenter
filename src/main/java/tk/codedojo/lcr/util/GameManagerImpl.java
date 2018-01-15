package tk.codedojo.lcr.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.codedojo.lcr.beans.DiceValues;

@Component
public class GameManagerImpl implements GameManager {
    @Autowired
    private Dice dice;
    @Autowired
    private GameHelper gameHelper;

    public GameManagerImpl(){
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
                System.out.println("player" + i + " has " + gameHelper.getPlayerCoins(i-1) + " coins left");
                for (int rolls = 0; rolls < gameHelper.getNumberOfRolls(gameHelper.getPlayerCoins(i-1)); rolls++) {
                    if(gameHelper.isLastRoll()){
                        endGame();
                    }
                    System.out.println(gameHelper.processRoll(dice.roll(), i));
                }
                gameHelper.removeCoinsFromPlayer(i-1);
            }
        }
    }
}
