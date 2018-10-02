package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.request.RouteLookupRequest;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookupFactory {
    public static RouteLookupRequest newRequest(Parcel parcel, Sender sender) {

        Long startPostOfficeID = parcel.getStartPostOffice().getId().getValue();
        Long finishtPostOfficeID = parcel.getFinishPostOffice().getId().getValue();

        return new RouteLookupRequest(startPostOfficeID, finishtPostOfficeID);
    }
}
