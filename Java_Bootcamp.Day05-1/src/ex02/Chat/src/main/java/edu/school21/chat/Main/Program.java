package edu.school21.chat.Main;

import edu.school21.chat.Chat.ChatRoom;
import edu.school21.chat.Chat.Message;
import edu.school21.chat.Chat.User;
import edu.school21.chat.Exception.NotSavedSubEntityException;
import edu.school21.chat.Repository.MessagesRepository;
import edu.school21.chat.Repository.MessagesRepositoryJdbcImpl;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(DataSource.getDataSource());
        try {
            // 1 test
            User creator = new User(1L, "user1", "1111", new ArrayList(), new ArrayList());
            User author = creator;
            ChatRoom room = new ChatRoom(1L, "1", creator, new ArrayList());
            Message message = new Message(null, author, room,  "Hello!", LocalDateTime.now());
            messagesRepository.saveMessage(message);
            System.out.println(message.getId()); // ex. id == 7
        }  catch (NotSavedSubEntityException nsse) {
            System.out.println(nsse);
        }
        // 2 test
        try {
            User author1 = new User(2L, "user2", "2222", new ArrayList(), new ArrayList());
            ChatRoom room1 = new ChatRoom(1L, "1", author1, new ArrayList());
            Message message1 = new Message(null, author1, room1, "Hello!", LocalDateTime.now());
            messagesRepository.saveMessage(message1);
            System.out.println(message1.getId()); // exception
        }  catch (NotSavedSubEntityException nsse) {
            System.out.println(nsse);
        }
    }
}