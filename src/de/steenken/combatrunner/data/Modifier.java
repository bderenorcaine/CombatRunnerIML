package de.steenken.combatrunner.data;


import java.util.HashMap;

/**
 * Created by dominik on 12/25/15.
 */
public class Modifier {
    private final HashMap<String, Integer> modifiers;

    private Modifier(final HashMap<String, Integer> modifiers) {
        this.modifiers = modifiers;
    }

    public static ModifierBuilder newBuilder() {
        return new ModifierBuilder();
    }

    public static class ModifierBuilder {
        private final HashMap<String, Integer> modifiers = new HashMap<>();

        public ModifierBuilder addModification(String name, int value) {
            modifiers.put(name, value);
            return this;
        }

        public Modifier build() {
            return new Modifier(modifiers);
        }
    }

    public final boolean modifies(final String name) {
        return modifiers.containsKey(name.toUpperCase());
    }

    public final int getModifier(final String name) {
        if (modifies(name.toUpperCase())) {
            return modifiers.get(name.toUpperCase());
        } else {
            return 0;
        }
    }
}
