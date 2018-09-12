package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Parser;
import com.alevel.deliverit.customers.Sender;

/**
 * @author Vitalii Usatyi
 */
public class ParcelReceptionRequestParser extends Parser<ParcelReceptionRequest> {

    @Override
    public ParcelReceptionRequest parse(String jsonString) {
        Parcel parcel = Parcel.parser().parse(jsonString);
        Sender sender = Sender.parser().parse(jsonString);
        return new ParcelReceptionRequest(sender, parcel);
    }
}
