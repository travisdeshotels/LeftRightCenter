package io.github.travisdeshotels.lcr.test;

import io.github.travisdeshotels.lcr.beans.DiceValues;
import org.junit.Test;
import static org.junit.Assert.*;

import io.github.travisdeshotels.lcr.util.GameHelper;
import io.github.travisdeshotels.lcr.util.GameHelperImpl;

public class TestGameHelper {
    @Test
    public void testGetNumberOfRolls(){
        GameHelper gameHelper = new GameHelperImpl();
        assertEquals(gameHelper.getNumberOfRolls(0), 0);
        assertEquals(gameHelper.getNumberOfRolls(1), 1);
        assertEquals(gameHelper.getNumberOfRolls(2), 2);
        assertEquals(gameHelper.getNumberOfRolls(3), 3);
        assertEquals(gameHelper.getNumberOfRolls(4), 3);
    }

    @Test
    public void testAnyCoinsLeft(){
        GameHelper gameHelper = new GameHelperImpl();
        assertTrue(gameHelper.anyCoinsLeft());
        gameHelper.removeCoinsFromPlayer(0, 3);
        gameHelper.removeCoinsFromPlayer(1, 3);
        assertFalse(gameHelper.anyCoinsLeft());
    }

    @Test
    public void testIsLastRoll(){
        GameHelper gameHelper = new GameHelperImpl();
        assertFalse(gameHelper.isLastRoll());
        gameHelper.removeCoinsFromPlayer(0, 3);
        gameHelper.removeCoinsFromPlayer(1, 2);
        assertTrue(gameHelper.isLastRoll());
    }

    @Test
    public void testPassLeft(){
        GameHelper gameHelper = new GameHelperImpl();
        gameHelper.passLeft(0);
        assertEquals(gameHelper.getPlayerCoins(0), 3);
        assertEquals(gameHelper.getPlayerCoins(1), 4);
        gameHelper.passLeft(1);
        assertEquals(gameHelper.getPlayerCoins(0), 4);
        assertEquals(gameHelper.getPlayerCoins(1), 4);
    }

    @Test
    public void testGetPlayerCoins(){
        GameHelper gameHelper = new GameHelperImpl();
        assertEquals(gameHelper.getNumberOfPlayers(), 2);
    }

    @Test
    public void testProcessRoll(){
        GameHelper gameHelper = new GameHelperImpl();
        assertEquals(gameHelper.processRoll(DiceValues.CENTER, 1), "player1 CENTER");
        assertEquals(gameHelper.processRoll(DiceValues.LEFT, 1), "player1 LEFT");
        assertEquals(gameHelper.processRoll(DiceValues.RIGHT, 1), "player1 RIGHT");
        assertEquals(gameHelper.processRoll(DiceValues.DOT1, 1), "player1 DOT");
    }

    @Test
    public void resetCoinChange(){
        GameHelper gameHelper = new GameHelperImpl();
        gameHelper.resetCoinChange();
        gameHelper.removeCoinsFromPlayer(1);
        assertEquals(gameHelper.getPlayerCoins(1), 3);
    }
}
