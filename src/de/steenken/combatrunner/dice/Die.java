package de.steenken.combatrunner.dice;

import java.util.Date;
import java.util.Random;

/**
 * A fair die.
 * @author Dominik Steenken
 */
public class Die {

    /**
     * Pseudo-random generator for the seeds for the individual dice.
     */
    private static final Random seedGenerator = new Random(new Date().getTime());

    /**
     * The number of sides the die has (default: 6)
     */
    private final int sides;

    /**
     * The pseudo-random number generator, initialized with the current time.
     */
    private final Random random = new Random(seedGenerator.nextLong());

    /**
     * The result of the last roll by this die.
     */
    private int lastRoll = 1;

    /**
     * Constructor for dice with a non-standard amount of sides.
     * @param sides The number of sides the die should have.
     */
    public Die(final int sides) {
        this.sides = sides;
    }

    /**
     * Constructor for a six-sided die.
     */
    public Die() {
        this.sides = 6;
    }

    /**
     * Roll the die. Returns a value within [1..#sides].
     * @return The result of the die roll.
     */
    public final int roll() {
        lastRoll = random.nextInt(sides) + 1;
        return lastRoll;
    }

    /**
     * Roll the die. When the highest possible value is rolled, roll again and add the results. Continue like this until a roll returns less than the maximum possible value.
     * @return The result of the die roll. Will be at least , is potentially unbounded (except for MAX_INT);
     */
    public final int rollExploding() {
        int rolled = roll();
        int result = rolled;
        while (rolled == sides) {
            rolled = roll();
            result += rolled;
            if (result + sides > Integer.MAX_VALUE) {
                lastRoll = Integer.MAX_VALUE;
                return Integer.MAX_VALUE;
            }
        }
        lastRoll = result;
        return result;
    }

    /**
     * Get the result last rolled with this die (its "current" value).
     * @return The "current" value of this die.
     */
    public final int get() {
        return lastRoll;
    }
}
