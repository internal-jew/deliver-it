package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.postal.network.PostOffice;

import java.util.Optional;

import static com.alevel.deliverit.logistics.postal.network.PostNetwork.instance;

/**
 * @author Sergey Bogovesov
 */
public class RouteLookupFactory {
    public static RouteLookupRequest newRequest(Parcel parcel, Sender sender) {

        Long startPostOfficeId = getPostOffice(parcel.getDeliveryAddress());
        Long finishPostOfficeId = getPostOffice(sender.getSenderProfile().getAddress());

        return new RouteLookupRequest(startPostOfficeId, finishPostOfficeId);
    }

    private static Long getPostOffice(PostalAddress postalAddress) {
        final Optional<PostOffice> postOffice = instance().find(postalAddress.getPostCode());
        if (postOffice.isPresent()) {
            return postOffice.get().getId().getValue();
        } else {
            throw new IllegalArgumentException("Post office with that post code not found!");
        }
    }
}
