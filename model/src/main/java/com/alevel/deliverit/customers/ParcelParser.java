package com.alevel.deliverit.customers;

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
class ParcelParser extends Parser<Parcel> {

    @Override
    public Parcel parse(String jsonString) {
        JSONObject jsonObject = parseToJsonObject(jsonString);
        Map parcelMap = (Map) jsonObject.get("parcel");
        double weightDouble = getWeightDouble(parcelMap);
        WeightUnit weightUnit = getWeightUnit(parcelMap);
        long id = (long) parcelMap.get("parcelId");
        ParcelId parcelId = buildParcelId(id);
        Weight weight = buildWeight(weightDouble, weightUnit);
        String postalAddressString = parcelMap.get("deliveryAddress").toString();
        PostalAddress deliveryAddress = PostalAddressParser.parser().parse(postalAddressString);
        return new Parcel(parcelId, weight, deliveryAddress);
    }

    private double getWeightDouble(Map parcelMap) {
        return Double.parseDouble((String) parcelMap.get("weight"));
    }

    private Weight buildWeight(double weight, WeightUnit weightUnit) {
        return new Weight(weight, weightUnit);
    }

    private ParcelId buildParcelId(long parcelId) {
        return new ParcelId(parcelId);
    }

    private WeightUnit getWeightUnit(Map parcelMap) {

        String weightUnit = (String) parcelMap.get("weightUnit");
        if (WeightUnit.KILOGRAM.toString().equalsIgnoreCase(weightUnit)) {
            return KILOGRAM;
        }
        if (WeightUnit.POUND.toString().equalsIgnoreCase(weightUnit)) {
            return POUND;
        } else {
            throw new IllegalArgumentException("Uncorrected weight unit");
        }
    }
}
