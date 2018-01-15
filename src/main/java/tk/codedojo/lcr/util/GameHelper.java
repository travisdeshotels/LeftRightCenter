package tk.codedojo.lcr.util;

import tk.codedojo.lcr.beans.DiceValues;

public interface GameHelper {
    int getNumberOfRolls(int coins);
    int getNumberOfPlayers();
    void passLeft(int playerIndex);
    boolean isLastRoll();
    boolean anyCoinsLeft();
    void removeCoinsFromPlayer(int player);
    void removeCoinsFromPlayer(int player, int coins);
    int getPlayerCoins(int player);
    void resetCoinChange();
    String processRoll(DiceValues roll, int player);
}
