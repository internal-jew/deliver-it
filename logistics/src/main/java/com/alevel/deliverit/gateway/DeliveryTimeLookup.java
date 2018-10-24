package com.alevel.deliverit.gateway;

import com.alevel.deliverit.BusinessLogicService;
import com.alevel.deliverit.DeliveryTime;
import com.alevel.deliverit.Subscribe;
import com.alevel.deliverit.logistics.DeliveryTimeRequest;
import com.alevel.deliverit.logistics.EstimatedDeliveryTime;

import static com.alevel.deliverit.SubscribeAddress.LOGISTICS_ESTIMATE_DELIVERY_TIME;

/**
 * @author Sergey Bogovesov
 */
public class DeliveryTimeLookup implements BusinessLogicService {

    @Subscribe(LOGISTICS_ESTIMATE_DELIVERY_TIME)
    public static EstimatedDeliveryTime estimate(DeliveryTimeRequest request) {
        final EstimatedDeliveryTime deliveryTime = new DeliveryTime().estimate(request.getParcel(), request.getRoute());
        System.out.println("Delivery time " + deliveryTime.getEstimatedDeliveryTime().toString());
        return deliveryTime;
    }
}
