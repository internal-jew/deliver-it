package com.alevel.deliverit.customers;

import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

/**
 * @author Vitalii Usatyi
 */
class SenderParser implements Parser<Sender> {


    SenderParser() {

    }

    @Override
    public Sender parse(String jsonString) {

        JSONObject jsonObject = parseToJsonObject(jsonString);
        Map senderMap = ((Map) jsonObject.get("sender"));
        Map senderProfileMap = (Map) senderMap.get("senderProfile");
        long id = (long) senderMap.get("senderId");
        String name = (String) senderProfileMap.get("name");
        String address = (String) senderProfileMap.get("address");
        String country = (String) senderProfileMap.get("country");
        String countryCode = (String) senderProfileMap.get("countryCode");
        SenderId senderId = buildSenderId(id);
        SenderProfile senderProfile = buildSenderProfile(name, address, country, countryCode);

        return new Sender(senderId, senderProfile);
    }

    @Override
    public JSONObject parseToJsonObject(String jsonString) {
        try {
            return (JSONObject) new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
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

    private SenderId buildSenderId(long senderId) {
        return new SenderId(senderId);
    }

}
