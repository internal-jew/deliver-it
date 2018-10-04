package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

public class SenderIdParser extends Parser<SenderId> {
    @Override
    public SenderId parse(String jsonSting) {
        JSONObject jsonObject = parseToJsonObject(jsonSting);
        long id = (long) jsonObject.get("value");
        return new SenderId(id);
    }
}
