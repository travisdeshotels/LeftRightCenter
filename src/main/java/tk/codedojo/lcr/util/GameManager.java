package tk.codedojo.lcr.util;

public interface GameManager {
    void passLeft(int playerIndex);
    int getNumberOfRolls(int coins);
    boolean isLastRoll();
    void endGame();
    boolean anyCoinsLeft();
    void runGame();
}
