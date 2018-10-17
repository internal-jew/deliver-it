package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.PostalAddressParser;
import com.alevel.deliverit.logistics.Weight;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.PostOfficeParser;
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
        String startPostOfficeJson = jsonObject.get("StartPostOffice").toString();
        String finishPostOfficeJson = jsonObject.get("FinishPostOffice").toString();
        long startPostOffice = PostOfficeParser.parser().parse(startPostOfficeJson);
        long finishPostOffice = PostOfficeParser.parser().parse(finishPostOfficeJson);
        return new Parcel(parcelId, weight, postalAddress, startPostOffice, finishPostOffice);
    }
}
