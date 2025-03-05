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
    private Long chatId;
    private String messageText;
    private LocalDateTime dateTime;

    public Message(String messageText, Long ownerId, Long chatId) {
        this.messageText = messageText;
        this.ownerId = ownerId;
        this.chatId = chatId;
    }
}
