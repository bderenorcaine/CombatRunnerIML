package de.steenken.combatrunner.data;

/**
 * Created by dominik on 12/25/15.
 */
public class Attribute implements Comparable<Attribute> {
    private final String name;

    private int value;

    public Attribute(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public Attribute(final String name) {
        this.name = name;
        this.value = 0;
    }


    @Override
    public int compareTo(Attribute attribute) {
        return Integer.compare(this.value, attribute.value);
    }

    int getValue() {
        return value;
    }
}
