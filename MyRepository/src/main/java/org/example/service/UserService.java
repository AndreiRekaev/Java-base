package org.example.service;

import org.example.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user) throws SQLException;
    Optional<User> getUserById(Long id) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    User updateUser(User user) throws SQLException;
    boolean deleteUser(Long id) throws SQLException;
    public boolean isUserEmailUnique(String email) throws SQLException;
}