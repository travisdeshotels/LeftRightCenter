package io.github.travisdeshotels.lcr.util;

import io.github.travisdeshotels.lcr.beans.DiceValues;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DiceImpl implements Dice{
    private Random random;

    public DiceImpl(){
        random = new Random();
    }

    public DiceValues roll(){
        int x = random.nextInt(6);
        if (x == 0) return DiceValues.LEFT;
        if (x == 1) return DiceValues.RIGHT;
        if (x == 2) return DiceValues.CENTER;
        if (x == 3) return DiceValues.DOT1;
        if (x == 4) return DiceValues.DOT2;
        return DiceValues.DOT3;
    }
}
