package com.alevel.deliverit.customers;

import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.WeightUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

import static com.alevel.deliverit.logistics.WeightUnit.KILOGRAM;
import static com.alevel.deliverit.logistics.WeightUnit.POUND;

/**
 * @author Vitalii Usatyi
 */
class ParcelParser implements Parser<Parcel> {

    ParcelParser() {

    }

    @Override
    public Parcel parse(String jsonString) {

        JSONObject jsonObject = parseToJsonObject(jsonString);
        Map parcelMap = (Map) jsonObject.get("parcel");
        Map deliveryAddressMap = (Map) parcelMap.get("deliveryAddress");
        String address = (String) deliveryAddressMap.get("address");
        String country = (String) deliveryAddressMap.get("country");
        String countryCode = (String) deliveryAddressMap.get("countryCode");
        double weightDouble = Double.parseDouble((String) parcelMap.get("weight"));
        long id = (long) parcelMap.get("parcelId");
        WeightUnit weightUnit = getWeightUnit(parcelMap);
        ParcelId parcelId = buildParcelId(id);
        Weight weight = buildWeight(weightDouble, weightUnit);
        PostalAddress deliveryAddress = buildPostalAddress(address, country, countryCode);

        return new Parcel(parcelId, weight, deliveryAddress);
    }

    @Override
    public JSONObject parseToJsonObject(String jsonString) {
        try {
            return (JSONObject) new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private PostalAddress buildPostalAddress(String address, String country, String countryCode) {
        return new PostalAddress(buildCountry(country, countryCode), address);
    }

    private Country buildCountry(String country, String countryCode) {
        return new Country(country, countryCode);
    }

    private Weight buildWeight(double weight, WeightUnit weightUnit) {
        return new Weight(weight, weightUnit);
    }

    private ParcelId buildParcelId(long parcelId) {
        return new ParcelId(parcelId);
    }

    private WeightUnit getWeightUnit(Map parcelMap) {
        String weightUnit = (String) parcelMap.get("weightUnit");
        if (WeightUnit.KILOGRAM.toString().toLowerCase().equals(weightUnit)) {
            return KILOGRAM;
        }
        if (WeightUnit.POUND.toString().toLowerCase().equals(weightUnit)) {
            return POUND;
        } else {
            throw new IllegalArgumentException("Uncorrected weight unit");
        }

    }
}
