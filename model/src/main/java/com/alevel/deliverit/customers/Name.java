package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;

/**
 * @author Vadym Mitin
 */
public class Name {
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
