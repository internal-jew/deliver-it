package com.alevel.deliverit.logistics.postal.network;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

/**
 * @author Vadym Mitin
 */
public class PostOfficeIdParser extends Parser<PostOfficeId> {
    @Override
    public PostOfficeId parse(String jsonSting) {
        JSONObject jsonObject = parseToJsonObject(jsonSting);
        long id = (long) jsonObject.get("value");
        return new PostOfficeId(id);
    }
}
