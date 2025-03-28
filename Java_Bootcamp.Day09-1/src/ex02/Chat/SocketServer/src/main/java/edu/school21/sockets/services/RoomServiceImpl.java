package edu.school21.sockets.services;

import edu.school21.sockets.models.ChatRoom;
import edu.school21.sockets.repositories.ChatRoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomServiceImpl implements RoomService {

    private ChatRoomsRepository chatRoomsRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RoomServiceImpl(ChatRoomsRepository chatRoomsRepository, PasswordEncoder passwordEncoder) {
        this.chatRoomsRepository = chatRoomsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean saveRoom(String name, Long ownerId) {
        ChatRoom chatRoom = new ChatRoom(name, ownerId);
        if(chatRoomsRepository.findByName(chatRoom.getChatName()).isPresent()) {
            return false;
        }
        chatRoomsRepository.save(chatRoom);
        return true;
    }

    @Override
    public boolean saveRoom(String name, String password, Long ownerId) {
        ChatRoom chatRoom = new ChatRoom(name, ownerId, password);
        chatRoom.setPassword(passwordEncoder.encode(chatRoom.getPassword()));
        chatRoomsRepository.save(chatRoom);
        return true;
    }

    @Override
    public boolean signIn(Long id, String password) {
        ChatRoom chatRoom = chatRoomsRepository.findById(id);
        return passwordEncoder.matches(password, chatRoom.getPassword());
    }

    @Override
    public List<ChatRoom> findAll() {
        List<ChatRoom> rooms = chatRoomsRepository.findAll();
        return rooms;
    }
}