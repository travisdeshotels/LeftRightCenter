package tk.codedojo.lcr.util;

import org.springframework.stereotype.Component;
import tk.codedojo.lcr.beans.DiceValues;
import tk.codedojo.lcr.beans.Player;

@Component
public class GameHelperImpl implements GameHelper{
    private Player[] players;
    private int coinChange;

    public GameHelperImpl(){
        this.players = new Player[2];
        players[0] = new Player("1");
        players[1] = new Player("2");
    }

    @Override
    public void resetCoinChange(){
        coinChange = 0;
    }

    @Override
    public String processRoll(DiceValues roll, int player){
        if (roll == DiceValues.CENTER) {
            coinChange++;
            return "player" + player + " CENTER";
        } else if (roll == DiceValues.LEFT) {
            this.passLeft(player-1);
            coinChange++;
            return "player" + player + " LEFT";
        } else if (roll == DiceValues.RIGHT) {
            this.passLeft(player-1);
            coinChange++;
            return "player" + player + " RIGHT";
        } else {
            return "player" + player + " DOT";
        }
    }

    @Override
    public void passLeft(int playerIndex) {
        if (playerIndex == 0){
            this.players[1].addCoins(1);
        } else{
            this.players[0].addCoins(1);
        }
    }

    @Override
    public boolean isLastRoll() {
        return this.players[0].getCoins() + this.players[1].getCoins() == 1;
    }

    @Override
    public boolean anyCoinsLeft() {
        return this.players[0].getCoins() + this.players[1].getCoins() > 0;
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
    public int getNumberOfPlayers(){
        return 2;
    }

    @Override
    public void removeCoinsFromPlayer(int player){
        this.players[player].removeCoins(coinChange);
    }

    @Override
    public void removeCoinsFromPlayer(int player, int coins){
        this.players[player].removeCoins(coins);
    }

    @Override
    public int getPlayerCoins(int player){
        return this.players[player].getCoins();
    }
}
