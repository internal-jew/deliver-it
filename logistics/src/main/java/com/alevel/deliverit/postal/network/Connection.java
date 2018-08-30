package com.alevel.deliverit.postal.network;

import java.util.Set;

/**
 * @author Sergey Bogovesov
 */
public class Connection {
    private PostalUnit startUnit;
    private PostalUnit endUnit;
    private Set<Limitation> limitations;

    //TODO add builder

    public Connection(PostalUnit startUnit, PostalUnit endUnit, Set<Limitation> limitations) {
        this.startUnit = startUnit;
        this.endUnit = endUnit;
        this.limitations = limitations;
    }

    public PostalUnit getStartUnit() {
        return startUnit;
    }

    public PostalUnit getEndUnit() {
        return endUnit;
    }

    public Set<Limitation> getLimitations() {
        return limitations;
    }
}
