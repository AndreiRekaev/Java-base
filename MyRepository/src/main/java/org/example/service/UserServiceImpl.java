package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user) {
        try {
            return repository.save(user);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to save user", e);
        }
    }

    public Optional<User> getUserById(Long id) {
        try {
            return repository.findById(id);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find user by id: " + id, e);
        }
    }

    public List<User> getAllUsers() {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to find all users", e);
        }
    }

    public User updateUser(User user) {
        try {
            return repository.update(user);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to update user: " + user.getId(), e);
        }
    }

    public boolean deleteUser(Long id) throws SQLException {
        try {
            return repository.delete(id);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete user: " + id, e);
        }
    }

    // Дополнительный метод бизнес-логики
    public boolean isUserEmailUnique(String email) {
        try {
            return repository.findAll().stream()
                    .noneMatch(user -> user.getEmail().equalsIgnoreCase(email));
        } catch (SQLException e) {
            throw new DataAccessException("Failed to check email uniqueness", e);
        }
    }
}

class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}