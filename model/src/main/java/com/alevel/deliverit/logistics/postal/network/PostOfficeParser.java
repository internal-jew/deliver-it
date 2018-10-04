package com.alevel.deliverit.logistics.postal.network;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

/**
 * @author Vadym Mitin
 */
public class PostOfficeParser extends Parser<PostOffice> {

    public static PostOfficeParser parser() {
        return new PostOfficeParser();
    }

    @Override
    public PostOffice parse(String jsonSting) {

        JSONObject jsonObject = parseToJsonObject(jsonSting);
//        String postOfficeId = jsonObject.get("PostOfficeId").toString();
        long id = (long) jsonObject.get("PostOfficeId");
        return PostOffice.builder()
                .setId(new PostOfficeId(id))
                .setName("Post unit " + id)
                .build();
    }
}
