package edu.school21.sockets.services;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessageRepository;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    private MessageRepository messageRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository, MessageRepository messageRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageRepository = messageRepository;
    }

    @Override
    public boolean signUp(String username, String password) {
        User user = new User();
        user.setPassword(password);
        user.setLogin(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (usersRepository.findByLogin(user.getLogin()).isPresent()) {
            return false;
        }
        usersRepository.save(user);
        return true;
    }

    @Override
    public Long signIn(String username, String password) {
        Optional<User> user = usersRepository.findByLogin(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get().getId();
        }
        return null;
    }

    @Override
    public void saveMessage(String messageText, Long userId) {
        Message message = new Message(messageText, userId);
        messageRepository.save(message);
    }
}