package edu.school21.sockets.serialization;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class JSONMessage {
    private String messageText;
    private LocalDateTime dateTime;

    public JSONMessage(String messageText) {
        this.messageText = messageText;
        dateTime = LocalDateTime.now();
    }
    public JSONMessage() {

    }
}
