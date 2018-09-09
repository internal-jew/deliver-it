package com.alevel.deliverit.customers;

import org.json.simple.JSONObject;

public interface Parser<T> {

    T parse(String json);

    JSONObject parseToJsonObject(String jsonString);
}

