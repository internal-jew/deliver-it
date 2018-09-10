package com.alevel.deliverit.moduleapi;

import java.util.function.Function;
/**
 * @author Vadym Mitin
 */
public class FunctionWrapper implements Function<String, String> {

    @Override
    public String apply(String s) {

        return s;
    }
}
