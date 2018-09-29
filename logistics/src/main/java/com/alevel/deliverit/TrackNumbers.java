package com.alevel.deliverit;

import com.alevel.deliverit.customers.Parcel;
import com.alevel.deliverit.logistics.TrackNumber;
import com.alevel.deliverit.logistics.TrackNumberId;

/**
 * @author Sergey Bogovesov
 */
public class TrackNumbers {

    public TrackNumber issue(Parcel parcel) {
        //TODO https://github.com/internal-jew/deliver-it/issues/13
        System.out.println("`TrackNumbers.issue` is not yet implemented");
        return new TrackNumber(new TrackNumberId("84654684"));
    }
}
