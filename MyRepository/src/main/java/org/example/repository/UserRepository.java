package org.example.repository;

import org.example.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user) throws SQLException;
    Optional<User> findById(Long id) throws SQLException;
    List<User> findAll() throws SQLException;
    User update(User user) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
