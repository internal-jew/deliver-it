package com.alevel.deliverit.customers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Vitalii Usatyi
 */
public abstract class Parser<T> {

    private T t;

    public T parse(String json) {
        return t;
    }

    JSONObject parseToJsonObject(String jsonString) {
        try {
            return (JSONObject) new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

