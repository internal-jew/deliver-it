package com.alevel.deliverit.customers;

import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;
import org.json.simple.JSONObject;

/**
 * @author Vitalii Usatyi
 */
public class PostalAddressParser extends Parser<PostalAddress> {

    private PostalAddressParser() {

    }

    static PostalAddressParser parser() {
        return new PostalAddressParser();
    }

    @Override
    public PostalAddress parse(String json) {
        JSONObject jsonObject = parseToJsonObject(json);
        String address = (String) jsonObject.get("address");
        String country = (String) jsonObject.get("country");
        String countryCode = (String) jsonObject.get("countryCode");
        return buildPostalAddress(address, country, countryCode);
    }

    private PostalAddress buildPostalAddress(String address, String country, String countryCode) {
        return new PostalAddress(buildCountry(country, countryCode), address);
    }

    private Country buildCountry(String country, String countryCode) {
        return new Country(country, countryCode);
    }
}
