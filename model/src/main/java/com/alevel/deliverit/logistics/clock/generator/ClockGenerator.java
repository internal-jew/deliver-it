package com.alevel.deliverit.logistics.clock.generator;

import com.alevel.deliverit.logistics.postal.network.PostOffice;

/**
 * @author Vadym Mitin
 */
public abstract class ClockGenerator {
    public abstract void generate();

    public abstract void registerPostOffice(PostOffice postOffice);
}
