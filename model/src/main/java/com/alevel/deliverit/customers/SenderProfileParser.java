package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;
import org.json.simple.JSONObject;

/**
 * @author Vitalii Usatyi
 */
public class SenderProfileParser extends Parser<SenderProfile> {

    @Override
    public SenderProfile parse(String jsonSting) {
        JSONObject jsonObject = parseToJsonObject(jsonSting);
        String nameJson = jsonObject.get("name").toString();
        Name name = Name.parser().parse(nameJson);
        String postalAddressJson = jsonObject.get("postalAddress").toString();
        PostalAddress postalAddress = PostalAddress.parser().parse(postalAddressJson);
        String countryJson = jsonObject.get("country").toString();
        Country country = Country.parser().parse(countryJson);
        return new SenderProfile(name, postalAddress, country);
    }
}
