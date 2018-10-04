package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;

/**
 * The {@code Name} class wraps a value of {@code String} name
 *
 * @author Vadym Mitin
 */
public final class Name {
    private final String name;

    public Name(String name) {
        this.name = name;
    }

    public static Parser<Name> parser() {
        return new NameParser();
    }

    public String getName() {
        return name;
    }
}
