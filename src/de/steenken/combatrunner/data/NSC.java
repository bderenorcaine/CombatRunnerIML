package de.steenken.combatrunner.data;

import de.steenken.combatrunner.dice.DicePool;

/**
 * Created by dominik on 26.12.15.
 */
public class NSC extends Combatant {
    public NSC(String name, int initiative, int initiativeDice) {
        super(name, initiative, initiativeDice);
    }

    @Override
    public void rollInitiative() {
        {
            currentInitiative = getInitiative() + DicePool.getDicePool(getInitiativeDice()).rollSum();
            actionsThisRound = 0;
            notifyObservers();
        }
    }


}
