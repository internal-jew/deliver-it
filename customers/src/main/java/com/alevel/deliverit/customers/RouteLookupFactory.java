package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.request.RouteLookupRequest;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookupFactory {
    public static RouteLookupRequest newRequest(Parcel parcel, Sender sender) {

//        Long startPostOfficeId = parcel.getStartPostOfficeId().getId().getValue();
//        Long finishPostOfficeId = parcel.getFinishPostOfficeId().getId().getValue();
        Long startPostOfficeId = parcel.getStartPostOfficeId();
        Long finishPostOfficeId = parcel.getFinishPostOfficeId();
        return new RouteLookupRequest(startPostOfficeId, finishPostOfficeId);
    }
}
