package com.alevel.deliverit.postal.network;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Sergey Bogovesov
 */
public class PostalUnit {
    private UUID id;
    private String postalUnitName;
    private Set<Connection> inputs;
    private Set<Connection> outputs;

    //TODO add builder

    public PostalUnit(UUID id) {
        this.id = id;
        inputs = new HashSet<>();
        outputs = new HashSet<>();
    }

    public PostalUnit(UUID id, Set<Connection> inputs, Set<Connection> outputs) {
        this.id = id;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public Set<Connection> getInputs() {
        return inputs;
    }

    public Set<Connection> getOutputs() {
        return outputs;
    }

    public void setInputs(Set<Connection> inputs) {
        this.inputs = inputs;
    }

    public void setOutputs(Set<Connection> outputs) {
        this.outputs = outputs;
    }

    public UUID getId() {
        return id;
    }
}
