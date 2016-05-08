package de.steenken.combatrunner.data;

/**
 * Created by dominik on 26.12.15.
 */
public class SC extends Combatant {

    private InitiativeProvider provider;

    protected SC(String name, InitiativeProvider provider) {
        super(name, 0, 0);
        this.provider = provider;
    }

    @Override
    public void rollInitiative() {
        currentInitiative = provider.getInitiative(getName());
        actionsThisRound = 0;
    }
}
