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
        String firstName = (String) jsonObject.get("firstName");
        String lastName = (String) jsonObject.get("lastName");
        String houseNumber = (String) jsonObject.get("houseNumber");
        String street = (String) jsonObject.get("street");
        String apartmentNumber = (String) jsonObject.get("apartmentNumber");
        String city = (String) jsonObject.get("city");
        String postalCode = (String) jsonObject.get("postalCode");
        Country country = Country.parser().parse(countryJson);
        return  PostalAddress.builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setHouseNumber(houseNumber)
                .setCountry(country)
                .setCity(city)
                .setApartmentNumber(apartmentNumber)
                .setPostalCode(postalCode)
                .setStreet(street)
                .build();
    }
}
