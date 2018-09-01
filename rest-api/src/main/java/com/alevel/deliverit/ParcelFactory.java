package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.ParcelId;
import com.alevel.deliverit.logistics.Country;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.WeightUnit;
import org.json.simple.JSONObject;

import java.util.Map;

import static com.alevel.deliverit.logistics.WeightUnit.KILOGRAM;
import static com.alevel.deliverit.logistics.WeightUnit.POUND;

/**
 * @author Vitalii Usatyi
 */
class ParcelFactory {

    Parcel create(JSONObject jsonObject){
        Map parcelMap = (Map) jsonObject.get("parcel");
        Map deliveryAddressMap =(Map)parcelMap.get("deliveryAddress");
        String address = (String)deliveryAddressMap.get("address") ;
        String country = (String)deliveryAddressMap.get("country");
        String countryCode=(String)deliveryAddressMap.get("countryCode");
        double weightDouble = Double.parseDouble ((String) parcelMap.get("weight"));
        long id = (long) parcelMap.get("parcelId");
        WeightUnit weightUnit = getWeightUnit(parcelMap);
        ParcelId parcelId = buildParcelId(id);
        Weight weight = buildWeight(weightDouble, weightUnit);
        PostalAddress deliveryAddress = buildPostalAddress(address, country, countryCode);

        return new Parcel(parcelId, weight, deliveryAddress);
    }

    private PostalAddress buildPostalAddress(String address, String country, String countryCode) {
        return new PostalAddress(buildCountry(country, countryCode),address);
    }

    private Country buildCountry(String country, String countryCode) {
        return new Country(country,countryCode);
    }

    private Weight buildWeight(double weight, WeightUnit weightUnit) {
        return new Weight(weight, weightUnit);
    }

    private ParcelId buildParcelId(long parcelId) {
        return new ParcelId(parcelId);
    }

    private WeightUnit getWeightUnit(Map parcelMap) {
        String weightUnit = (String) parcelMap.get("weightUnit");
        if (weightUnit.equals("kilogram")) {
            return KILOGRAM;
        }
        if (weightUnit.equals("pound")){
            return POUND;
        }else {
            throw new IllegalArgumentException();
        }

    }
}
