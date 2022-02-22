package tk.codedojo.lcr.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import tk.codedojo.lcr.beans.DiceValues;
import tk.codedojo.lcr.util.*;

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
