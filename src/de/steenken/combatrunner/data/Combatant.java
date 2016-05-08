package de.steenken.combatrunner.data;

import de.steenken.combatrunner.dice.DicePool;
import de.steenken.combatrunner.util.InsufficientInitiativeException;

import java.util.Observable;

/**
 * Created by dominik on 12/25/15.
 */
public abstract class Combatant extends Observable implements Comparable<Combatant> {

    private final AttributeArray attributes;

    private final String name;

    protected int currentInitiative = 0;

    protected int actionsThisRound = 0;

    public static Combatant makeNSC(final String name, final int initiative, final int initiativeDice) {
        return new NSC(name, initiative,initiativeDice);
    }

    public static Combatant makeSC(final String name, final InitiativeProvider provider) {
        return new SC(name, provider);
    }

    protected Combatant(final String name, final int initiative, final int initiativeDice) {
        this.attributes = new AttributeArray(initiative, initiativeDice);
        this.name = name;
    }

    public abstract void rollInitiative();

    public final int getInitiative() {
        return attributes.getValue("INITIATIVE");
    }

    public final int getInitiativeDice() {
        return attributes.getValue("INITIATIVEDICE");
    }

    public void act(final int initiativePoints) {
        if (currentInitiative <= 0) {
            throw new InsufficientInitiativeException();
        }
        currentInitiative -= initiativePoints;
        if (initiativePoints == 10) {
            actionsThisRound++;
        }
        setChanged();
        notifyObservers();
    }

    public final int getCurrentInitiative() {
        return currentInitiative;
    }

    @Override
    public int compareTo(Combatant combatant) {
        if (currentInitiative > 0 && combatant.currentInitiative <= 0) {
            return -1;
        } else if (currentInitiative <= 0 && combatant.currentInitiative > 0) {
            return 1;
        } else if (actionsThisRound > combatant.actionsThisRound) {
            return 1;
        } else if (actionsThisRound < combatant.actionsThisRound) {
            return -1;
        } else {
            return combatant.currentInitiative - currentInitiative;
        }
    }

    public final String toString() {
        return "[" + name + ", " + currentInitiative + "]";
    }

    public final String getName() {
        return name;
    }
}
