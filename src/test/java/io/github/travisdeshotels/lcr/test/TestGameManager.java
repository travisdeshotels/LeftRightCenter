package io.github.travisdeshotels.lcr.test;

import io.github.travisdeshotels.lcr.beans.DiceValues;
import io.github.travisdeshotels.lcr.util.Dice;
import io.github.travisdeshotels.lcr.util.GameHelperImpl;
import io.github.travisdeshotels.lcr.util.GameManagerImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TestGameManager {
    @Mock
    private Dice dice;

    @InjectMocks
    private GameManagerImpl gm;

    @Spy
    private GameHelperImpl gameHelper;

    @Ignore
    @Test
    public void test() {
        //doReturn(DiceValues.CENTER).when(dice).roll();
        doReturn(DiceValues.DOT1).
                doReturn(DiceValues.CENTER).
                doReturn(DiceValues.CENTER).
                doReturn(DiceValues.CENTER).
                doReturn(DiceValues.CENTER).
                doReturn(DiceValues.CENTER).
                doReturn(DiceValues.DOT1).
                when(dice).roll();
        gm.runGame();
    }
}
