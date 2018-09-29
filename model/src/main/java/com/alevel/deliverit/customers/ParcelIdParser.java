package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

public class ParcelIdParser extends Parser<ParcelId> {
    @Override
    public ParcelId parse(String jsonSting) {

        JSONObject jsonObject = parseToJsonObject(jsonSting);
        long id = (long) jsonObject.get("value");
        return new ParcelId(id);
    }
}
