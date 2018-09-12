package com.alevel.deliverit.customers;

import org.json.simple.JSONObject;

import java.util.Map;

/**
 * @author Vitalii Usatyi
 */
class SenderParser extends Parser<Sender> {

    @Override
    public Sender parse(String jsonString) {

        JSONObject jsonObject = parseToJsonObject(jsonString);
        Map senderMap = ((Map) jsonObject.get("sender"));
        long id = (long) senderMap.get("senderId");
        SenderId senderId = buildSenderId(id);
        String senderProfileString = senderMap.get("senderProfile").toString();
        SenderProfile senderProfile = SenderProfileParser.parser().parse(senderProfileString);
        return new Sender(senderId, senderProfile);
    }

    private SenderId buildSenderId(long senderId) {
        return new SenderId(senderId);
    }
}
