package com.alevel.deliverit.customers.factory;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.customers.Sender;
import com.alevel.deliverit.customers.request.PriceLookupRequest;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.logistics.DeliveryTimeRequest;
import com.alevel.deliverit.logistics.PostalAddress;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.Route;

import java.util.Optional;

import static com.alevel.deliverit.logistics.postal.network.PostNetwork.instance;

/**
 * @author Sergey Bogovesov
 */
public class RequestLookupFactory {
    public static RouteLookupRequest newRouteRequest(Parcel parcel, Sender sender) {

        Long startPostOfficeId = getPostOffice(parcel.getDeliveryAddress());
        Long finishPostOfficeId = getPostOffice(sender.getSenderProfile().getAddress());

        return new RouteLookupRequest(startPostOfficeId, finishPostOfficeId);
    }

    public static PriceLookupRequest newPriceRequest(Parcel parcel, Route route) {
        return new PriceLookupRequest(parcel.getWeight(), route);
    }

    public static DeliveryTimeRequest newDeliveryTimeRequest(Parcel parcel, Route route) {
        return new DeliveryTimeRequest(parcel, route);
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
