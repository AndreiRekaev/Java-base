package edu.school21.sockets.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    private Long id;
    private String chatName;
    private Long chatOwner;
    private String password;

    public ChatRoom(String chatName, Long chatOwner) {
        this.chatName = chatName;
        this.chatOwner = chatOwner;
    }
    public ChatRoom(String chatName, Long chatOwner, String password) {
        this(chatName, chatOwner);
        this.password = password;
    }
}