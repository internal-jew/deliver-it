package com.alevel.deliverit.logistics.postal.network;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

import java.util.Optional;

/**
 * @author Vadym Mitin
 */
public class PostOfficeParser extends Parser<Long> {

    public static PostOfficeParser parser() {
        return new PostOfficeParser();
    }

    @Override
    public Long parse(String jsonSting) {

        JSONObject jsonObject = parseToJsonObject(jsonSting);
        long id = (long) jsonObject.get("id");
//        JSONObject jsonObject = parseToJsonObject(jsonSting);
//        PostOfficeId postOfficeId = PostOfficeId.parser().parse(jsonObject.get("PostOfficeId").toString());
//        PostNetwork instance = PostNetwork.instance();
//        Long value = postOfficeId.getValue();
//        Optional<PostOffice> postOffice = instance.find(value);
//        return postOffice.orElseThrow(() -> new IllegalArgumentException("cant find Post office by id"));
        return id;
    }
}
