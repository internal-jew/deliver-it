package com.alevel.deliverit.logistics;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

/**
 * @author Vitalii Usatyi
 */
public class CountryParser extends Parser<Country> {

    @Override
    public Country parse(String jsonSting) {
        JSONObject jsonObject = parseToJsonObject(jsonSting);
        String country = (String) jsonObject.get("country");
        String countryCode = (String) jsonObject.get("countryCode");
        return new Country(country, countryCode);
    }
}
