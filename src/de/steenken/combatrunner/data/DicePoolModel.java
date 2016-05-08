package de.steenken.combatrunner.data;

import de.steenken.combatrunner.dice.DicePool;

import java.time.LocalDateTime;

/**
 * Created by dominik on 08.05.16.
 */
public class DicePoolModel {

    private final int basePool;

    private final int edgeDice;

    private boolean useEdge;

    private int modifier;

    private LocalDateTime lastRolled = null;

    private int lastNumSuccesses = 0;

    private boolean glitch = false;

    public DicePoolModel(final int numberOfDice, final int edgeDice) {
        if (numberOfDice < 0 || edgeDice < 0) {
            throw new IllegalArgumentException("Error - dicepools can't be negative (base " + numberOfDice + ", edge " + edgeDice +")");
        }
        this.basePool = numberOfDice;
        this.edgeDice = edgeDice;
        useEdge = false;
        modifier = 0;
    }

    public void roll() {
        DicePool effectivePool = getEffectiveBaseDicePool();
        if (!useEdge) {
            lastNumSuccesses = effectivePool.rollSimple();
        } else {
            lastNumSuccesses = effectivePool.rollWithEdge(DicePool.getDicePool(edgeDice));
        }
        glitch = effectivePool.getLastOnesRolled() >= effectivePool.size() / 2;
        lastRolled = LocalDateTime.now();
    }

    public int getLastNumSuccesses() {
        return lastNumSuccesses;
    }

    public boolean lastRollWasGlitch() {
        return glitch;
    }

    public boolean lastRollWasCriticalGlitch() {
        return glitch && lastNumSuccesses == 0;
    }

    public long getSecondsSinceLastRoll() {
        if (lastRolled == null) {
            return 0;
        } else {
            return java.time.Duration.between(lastRolled, LocalDateTime.now()).getSeconds();
        }
    }

    public int getModifier() {
        return modifier;
    }

    public int getBasePool() {
        return basePool;
    }

    public int getEdgeDice() {
        return edgeDice;
    }

    public int getEffectiveDicePoolSize() {
        return getEffectiveBaseDicePool().size();
    }

    private DicePool getEffectiveBaseDicePool() {
        // no modifier
        if (modifier == 0) {
            return DicePool.getDicePool(basePool);
        // positive modifier or negative modifier smaller than dice pool
        } else if (modifier + basePool > 0) {
            return DicePool.getDicePool(basePool + modifier);
        // negative modifier equal to or larger than dice pool
        } else {
            return DicePool.getDicePool(0);
        }
    }

    public void setModifier(final int modifier) {
        this.modifier = modifier;
    }

    public void setUseEdge(final boolean useEdge) {
        this.useEdge = useEdge;
    }
}
