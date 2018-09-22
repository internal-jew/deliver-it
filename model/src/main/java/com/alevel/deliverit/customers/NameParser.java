package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

public class NameParser extends Parser<Name> {

    @Override
    public Name parse(String jsonSting) {
        JSONObject jsonObject = parseToJsonObject(jsonSting);
        String name = (String) jsonObject.get("name");
        return new Name(name);
    }
}
