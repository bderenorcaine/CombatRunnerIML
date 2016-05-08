package de.steenken.combatrunner.ui;

import de.steenken.combatrunner.data.InitiativeProvider;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by dominik on 26.12.15.
 */
public class AskTheUser implements InitiativeProvider {
    @Override
    public int getInitiative(final String name) {
        String answer = UIController.getTextFromUser("Initiative for " + name, "Initiative for " + name, "Initiative score rolled for " + name + ":");
        return Integer.parseInt(answer);
    }
}
