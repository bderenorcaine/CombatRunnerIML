package de.steenken.combatrunner.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by dominik on 12/25/15.
 */
public class AttributeArray {
    private final HashMap<String, Attribute> attributes = new HashMap<>();
    private final LinkedList<Modifier> modifiers = new LinkedList<>();

    public AttributeArray(final int initiative, final int initiativeDice) {
        attributes.put("INITIATIVE", new Attribute("INITIATIVE", initiative));
        attributes.put("INITIATIVEDICE", new Attribute("INITIATIVEDICE", initiativeDice));
    }

    public int getValue(final String attributeName) {
        String name = attributeName.toUpperCase();
        if (attributes.containsKey(name)) {
            int value = attributes.get(name).getValue();
            for (Modifier modifier : modifiers) {
                value += modifier.getModifier(name);
            }
            return value;
        } else {
            throw new NoSuchElementException("Attribute " + name + " does not exist.");
        }
    }
}
