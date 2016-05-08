package de.steenken.combatrunner.data;


import de.steenken.combatrunner.util.EndOfRoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Created by dominik on 12/25/15.
 */
public class InitiativeList implements Observer {

    private final ObservableList<Combatant> combatants = FXCollections.observableArrayList();
    private boolean ignoreUpdates = false;

    public InitiativeList() {

    }

    public ObservableList<Combatant> getReadOnlyList() {
        return FXCollections.unmodifiableObservableList(combatants);
    }

    public void addCombatant(final Combatant combatant) {
        combatants.add(combatant);
        combatant.addObserver(this);
        sort();
    }

    private void sort() {
        Collections.sort(combatants);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (!ignoreUpdates) {
            sort();
        }
    }

    public Combatant nextCombatant() throws EndOfRoundException{
        if (combatants.get(0).getCurrentInitiative() <= 0) {
            throw new EndOfRoundException();
        } else {
            return combatants.get(0);
        }
    }

    public final void nextRound() {
        ignoreUpdates = true;
        for (Combatant combatant : combatants) {
            combatant.rollInitiative();
        }
        ignoreUpdates = false;
        sort();
    }

    public final String toString() {
        StringBuffer buffer = new StringBuffer("====Current Initiative Order====\n");
        for (Combatant combatant : combatants) {
            buffer.append("|--" + combatant.toString() + "\n");
        }
        buffer.append("================================\n");
        return buffer.toString();
    }

}
