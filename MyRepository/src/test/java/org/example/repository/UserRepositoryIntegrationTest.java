package org.example.repository;

import org.example.model.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserRepositoryIntegrationTest {

    private static Connection connection;
    private static UserRepositoryImpl userRepository;

    @BeforeAll
    static void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE users (id BIGINT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255))");
        }
        userRepository = new UserRepositoryImpl(connection);
    }

    @AfterEach
    void cleanUp() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users");
        }
    }

    @AfterAll
    static void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    void saveUserShouldPersistData() throws SQLException {
        User user = new User(null, "test_user", "test@example.com");

        User savedUser = userRepository.save(user);

        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals("test_user", savedUser.getUsername());
        Assertions.assertEquals("test@example.com", savedUser.getEmail());
    }

    @Test
    void findByIdShouldReturnExistingUser() throws SQLException {
        User savedUser = userRepository.save(new User(null, "existing_user", "existing@test.com"));

        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(savedUser.getId(), foundUser.get().getId());
        Assertions.assertEquals("existing_user", foundUser.get().getUsername());
    }

    @Test
    void findAllShouldReturnAllUsers() throws SQLException {
        User user1 = userRepository.save(new User(null, "user1", "u1@test.com"));
        User user2 = userRepository.save(new User(null, "user2", "u2@test.com"));

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(2, users.size());
        Assertions.assertTrue(users.contains(user1));
        Assertions.assertTrue(users.contains(user2));
    }

    @Test
    void updateUserShouldModifyExistingRecord() throws SQLException {
        User savedUser = userRepository.save(new User(null, "old_username", "old@test.com"));

        User updatedUser = new User(savedUser.getId(), "new_username", "new@test.com");
        userRepository.update(updatedUser);

        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("new_username", foundUser.get().getUsername());
        Assertions.assertEquals("new@test.com", foundUser.get().getEmail());
    }

    @Test
    void deleteShouldRemoveUser() throws SQLException {
        User savedUser = userRepository.save(new User(null, "delete_user", "del@test.com"));

        boolean deleted = userRepository.delete(savedUser.getId());

        Assertions.assertTrue(deleted);
        Assertions.assertFalse(userRepository.findById(savedUser.getId()).isPresent());
    }
}