package com.alevel.deliverit.gateway;

import com.alevel.deliverit.BusinessLogicService;
import com.alevel.deliverit.EstimatedPriceCalculator;
import com.alevel.deliverit.Subscribe;
import com.alevel.deliverit.billing.Money;
import com.alevel.deliverit.customers.request.PriceLookupRequest;

import static com.alevel.deliverit.SubscribeAddress.BILLING_CALCULATE_PRICE;

/**
 * @author Sergey Bogovesov
 */
public class PriceLookup implements BusinessLogicService {

    @Subscribe(BILLING_CALCULATE_PRICE)
    public static Money calculate(PriceLookupRequest request) {
        EstimatedPriceCalculator estimatedPriceCalculator = new EstimatedPriceCalculator();
        final Money money = estimatedPriceCalculator.calculate(request.getParcelWeight(), request.getRoute());
        System.out.println("Price parcel: " + money.getValue());
        return money;
    }
}
