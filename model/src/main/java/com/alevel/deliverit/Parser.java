package com.alevel.deliverit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Vitalii Usatyi
 */
public abstract class Parser<T> {

    private T t;

    public T parse(String jsonSting) {
        return t;
    }

    public JSONObject parseToJsonObject(String jsonString) {
        try {
            return (JSONObject) new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

