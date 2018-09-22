package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.PostalAddressParser;
import com.alevel.deliverit.logistics.Weight;
import org.json.simple.JSONObject;

/**
 * @author Vitalii Usatyi
 */
class ParcelParser extends Parser<Parcel> {

    @Override
    public Parcel parse(String jsonSting) {
        JSONObject jsonObject = parseToJsonObject(jsonSting);
        String parcelIdJson = jsonObject.get("parcelId").toString();
        ParcelId parcelId = ParcelId.parser().parse(parcelIdJson);
        String weightJson = jsonObject.get("weight").toString();
        Weight weight = Weight.parser().parse(weightJson);
        String postalAddressJson = jsonObject.get("postalAddress").toString();
        PostalAddress postalAddress = PostalAddressParser.parser().parse(postalAddressJson);
        return new Parcel(parcelId, weight, postalAddress);
    }
}
