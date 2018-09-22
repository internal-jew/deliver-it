package com.alevel.deliverit.logistics;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

/**
 * @author Vitalii Usatyi
 */
public class PostalAddressParser extends Parser<PostalAddress> {

    public static PostalAddressParser parser() {
        return new PostalAddressParser();
    }

    @Override
    public PostalAddress parse(String jsonSting) {

        JSONObject jsonObject = parseToJsonObject(jsonSting);
        String countryJson = jsonObject.get("country").toString();
        Country country = Country.parser().parse(countryJson);
        String address = (String) jsonObject.get("address");
        return new PostalAddress(country, address);
    }
}
