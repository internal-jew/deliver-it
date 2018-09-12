package com.alevel.deliverit.customers;

import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;
import org.json.simple.JSONObject;

/**
 * @author Vitalii Usatyi
 */
public class SenderProfileParser extends Parser<SenderProfile> {

    private SenderProfileParser() {

    }

    static SenderProfileParser parser() {
        return new SenderProfileParser();
    }


    @Override
    public SenderProfile parse(String json) {
        JSONObject jsonObject = parseToJsonObject(json);
        String name = (String) jsonObject.get("name");
        String address = (String) jsonObject.get("address");
        String country = (String) jsonObject.get("country");
        String countryCode = (String) jsonObject.get("countryCode");

        return buildSenderProfile(name, address, country, countryCode);
    }

    private SenderProfile buildSenderProfile(String name, String address, String country, String countryCode) {
        return new SenderProfile(buildName(name), buildAddress(address, country, countryCode), buildCountry(country, countryCode));
    }

    private PostalAddress buildAddress(String address, String country, String countryCode) {
        return new PostalAddress(buildCountry(country, countryCode), address);
    }

    private Name buildName(String name) {
        return new Name(name);
    }

    private Country buildCountry(String country, String countryCode) {
        return new Country(country, countryCode);
    }
}
