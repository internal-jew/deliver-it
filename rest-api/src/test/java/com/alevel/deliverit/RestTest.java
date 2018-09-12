package com.alevel.deliverit;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
@DisplayName("Rest API should:")
class RestTest {

    @Test
    @DisplayName("Parse json to Object")
    void testParseToObject() {
//        ObjectParser objectParser = new ObjectParser();
        Endpoint endpoint = new Endpoint();
        try {
            File file = new File("E:\\Java\\Java_test\\JsonRequest.json");
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(file));
            String jsonString = jsonObject.toJSONString();
            endpoint.run(jsonString);

//            System.out.println(jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
