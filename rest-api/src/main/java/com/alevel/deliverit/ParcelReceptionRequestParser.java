package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Sender;
import org.json.simple.JSONObject;

/**
 * Gets {@link Sender} and {@link Parcel} from Json reques and make a {@link ParcelReceptionRequest}
 *
 * @author Vitalii Usatyi
 */
public class ParcelReceptionRequestParser extends Parser<ParcelReceptionRequest> {

    @Override
    public ParcelReceptionRequest parse(String jsonSting) {
        JSONObject jsonObject = parseToJsonObject(jsonSting);
        String senderJson = jsonObject.get("sender").toString();
        String parcelJson = jsonObject.get("parcel").toString();

        Sender sender = Sender.parser().parse(senderJson);
        Parcel parcel = Parcel.parser().parse(parcelJson);
        return new ParcelReceptionRequest(sender, parcel);
    }
}
