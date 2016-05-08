package de.steenken.combatrunner.test;

import static org.junit.Assert.*;

import de.steenken.combatrunner.dice.Die;

/**
 * Created by dominik on 12/25/15.
 */
public class DieTest {

    public final int ROLL_LIMIT = 100000;

    public final int SIDES_LIMIT = 100;

    @org.junit.Test
    public void testRoll() throws Exception {
        for (int sides = 2; sides < SIDES_LIMIT; ++sides) {
            int[] results = new int[ROLL_LIMIT];
            Die die = new Die(sides);
            for (int i = 0; i < ROLL_LIMIT; ++i) {
                results[i] = die.roll();
            }
            int[] tally = new int[sides + 1];
            for (int i : results) {
                if (i < 1 || i > sides) {
                    tally[0]++;
                } else {
                    tally[i]++;
                }
            }
            if (tally[0] > 0) {
                fail("Observed die results outside of range");
            }
            for (int i = 1; i <= sides; ++i) {
                if (Math.abs(tally[i] - (ROLL_LIMIT / sides)) > ROLL_LIMIT / 100.0) {
                    fail("Tally for " + sides + "-sided dice and side " + i + " is off by " + Math.abs(tally[i] - ROLL_LIMIT / sides) + " (with a total of " + ROLL_LIMIT + " rolls)");
                }
            }
        }
    }
}