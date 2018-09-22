package com.alevel.deliverit.logistics;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

import static com.alevel.deliverit.logistics.WeightUnit.KILOGRAM;
import static com.alevel.deliverit.logistics.WeightUnit.POUND;

public class WeightParser extends Parser<Weight> {

    @Override
    public Weight parse(String jsonSting) {
        JSONObject jsonObject = parseToJsonObject(jsonSting);
        WeightUnit weightUnit = getWeightUnit(jsonObject);
        double weightDouble = (double) jsonObject.get("value");
        return new Weight(weightDouble, weightUnit);
    }

    private WeightUnit getWeightUnit(JSONObject jsonObject) {
        String weightUnit = (String) jsonObject.get("weightUnit");
        if (KILOGRAM.value.equalsIgnoreCase(weightUnit)) {
            return KILOGRAM;
        }
        if (POUND.value.equalsIgnoreCase(weightUnit)) {
            return POUND;
        } else {
            throw new IllegalArgumentException("Invalid weight unit");
        }
    }
}
