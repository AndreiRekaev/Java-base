package edu.school21.sockets.repositories;

import edu.school21.sockets.models.ChatRoom;
import edu.school21.sockets.models.User;

import java.util.Optional;

public interface ChatRoomsRepository extends CrudRepository<ChatRoom> {
    Optional<ChatRoom> findByName(String name);
}
