package io.github.travisdeshotels.lcr.util;

import io.github.travisdeshotels.lcr.beans.DiceValues;

@FunctionalInterface
public interface Dice {
    DiceValues roll();
}
