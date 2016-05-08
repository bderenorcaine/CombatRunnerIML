package de.steenken.combatrunner.dice;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dominik on 12/25/15.
 */
public class DicePool {

    private static final HashMap<Integer, DicePool> dicePools = new HashMap<>();

    public static final DicePool getDicePool(final int numberOfDice) {
        if (!dicePools.containsKey(numberOfDice)) {
            dicePools.put(numberOfDice, new DicePool(numberOfDice));
        }
        return dicePools.get(numberOfDice);
    }

    private final ArrayList<Die> dice;

    private int lastSuccesses = 0;

    private int lastOnes = 0;

    private final int successThreshold;

    private DicePool(final int numberOfDice, final int sides, final int successThreshold) {
        dice = new ArrayList<>(numberOfDice);
        this.successThreshold = successThreshold;
        initializeDice(numberOfDice, sides);
    }

    private DicePool(final int numberOfDice) {
        dice = new ArrayList<>(numberOfDice);
        this.successThreshold = 5;
        initializeDice(numberOfDice, 6);
    }

    private void initializeDice(final int numberOfDice, final int sides) {
        for (int i = 0; i < numberOfDice; ++i) {
            dice.add(new Die(sides));
        }
    }

    private int rollDiceListSimple(final ArrayList<Die> diceList) {
        int successes = 0;
        for (Die die : diceList) {
            int result = die.roll();
            if (result >= successThreshold) {
                successes++;
            } if (result == 1) {
                lastOnes++;
            }
        }
        return successes;
    }

    public final int size() {
        return dice.size();
    }

    public final int rollSimple() {
        lastOnes = 0;
        lastSuccesses = rollDiceListSimple(dice);
        return lastSuccesses;
    }

    final int rollExploding() {
        // this list will contain all dice that created a success (and initially, all dice).
        ArrayList<Die> successDice = new ArrayList<>(size());
        successDice.addAll(dice);
        int successes = 0;
        lastOnes = 0;
        // while we still have dice, keep rolling them
        while (successDice.size() > 0) {
            // roll the dice
            System.out.print("Rolling " + successDice.size() + " dice...");
            successes += rollDiceListSimple(successDice);
            System.out.println(successes + " total successes");
            // remove all dice that did not generate a success
            ArrayList<Die> failures = new ArrayList<>(size());
            for (Die die : successDice) {
                if (die.get() < successThreshold) {
                    failures.add(die);
                }
            }
            successDice.removeAll(failures);
        }
        System.out.println("No dice remain.");
        lastSuccesses = successes;
        return successes;
    }

    public int getLastOnesRolled() {
        return lastOnes;
    }

    public final int rollWithEdge(final DicePool edgePool) {
        return rollExploding() + edgePool.rollExploding();
    }

    public final int rollSum() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.roll();
        }
        return sum;
    }

    public int rerollFailures() {
        ArrayList<Die> failures = new ArrayList<>(size());
        for (Die die : dice) {
            if (die.get() < successThreshold) {
                failures.add(die);
            }
        }
        System.out.println("Rerolling " + failures.size() + " dice.");
        int successes = rollDiceListSimple(failures);
        lastSuccesses += successes;
        return lastSuccesses;
    }

    public static final void main(String[] args) {
        int result = DicePool.getDicePool(10).rollSimple();
        System.out.println("Rolled " + result + " successes on the first try. Rerolling failures...");
        result = DicePool.getDicePool(10).rerollFailures();
        System.out.println("Rolled " + result + " successes total.");
    }
}
