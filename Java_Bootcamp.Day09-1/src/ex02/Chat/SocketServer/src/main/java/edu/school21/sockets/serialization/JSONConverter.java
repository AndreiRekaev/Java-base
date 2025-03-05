package edu.school21.sockets.serialization;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.LocalDateTime;

public class JSONConverter {
    private JSONConverter() {}

    private static final String TAG_MSG = "message";
    private static final String TAG_TIME = "time";

    public static JSONMessage parseToObject(String msg) {
        JSONMessage newMessage = new JSONMessage();
        JSONParser parser = new JSONParser();
        try {
            JSONObject messageObject = (JSONObject) parser.parse(msg);
            newMessage.setMessageText((String) messageObject.get(TAG_MSG));
            String time = (String) messageObject.get(TAG_TIME);
            newMessage.setDateTime(LocalDateTime.parse(time));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newMessage;
    }

    public static String makeJSON(String msg) {
        try {
            JSONMessage message = new JSONMessage(msg);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(TAG_MSG, msg);
            jsonObject.put(TAG_TIME, LocalDateTime.now().toString());
            return jsonObject.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}