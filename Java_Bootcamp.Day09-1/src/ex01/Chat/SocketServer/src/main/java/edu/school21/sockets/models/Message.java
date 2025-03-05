package edu.school21.sockets.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Long id;
    private Long ownerId;
    private String messageText;
    private LocalDateTime dateTime;

    public Message(String messageText, Long ownerId) {
        this.messageText = messageText;
        this.ownerId = ownerId;
    }
}
