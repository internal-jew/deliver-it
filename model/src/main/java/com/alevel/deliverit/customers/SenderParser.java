package com.alevel.deliverit.customers;

import com.alevel.deliverit.Parser;
import org.json.simple.JSONObject;

/**
 * @author Vitalii Usatyi
 */
class SenderParser extends Parser<Sender> {

    @Override
    public Sender parse(String jsonSting) {
        JSONObject jsonObject = parseToJsonObject(jsonSting);
        String senderIdJson = jsonObject.get("senderId").toString();
        String senderProfileJson = jsonObject.get("senderProfile").toString();
        SenderId senderId = SenderId.parser().parse(senderIdJson);
        SenderProfile senderProfile = SenderProfile.parser().parse(senderProfileJson);
        return new Sender(senderId, senderProfile);
    }
}
