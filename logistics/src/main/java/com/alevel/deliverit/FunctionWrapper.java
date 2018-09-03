package com.alevel.deliverit;

import java.util.function.Function;

public class FunctionWrapper implements Function<String, String> {

    @Override
    public String apply(String s) {

        return s;
    }
}
