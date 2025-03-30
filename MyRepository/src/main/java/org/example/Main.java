package org.example;

import org.example.service.UserServiceImpl;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.example.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "")) {
            initializeDatabase(connection);

            UserRepository userRepository = new UserRepositoryImpl(connection);
            UserService userService = new UserServiceImpl(userRepository);

            System.out.println("=== Creating users ===");
            User user1 = userService.createUser(new User(1L, "john_doe", "john@example.com"));
            User user2 = userService.createUser(new User(2L, "jane_smith", "jane@example.com"));
            System.out.println("Created users: " + user1 + ", " + user2);

            System.out.println("\n=== Finding all users ===");
            List<User> allUsers = userService.getAllUsers();
            allUsers.forEach(System.out::println);

            System.out.println("\n=== Finding user by ID ===");
            userService.getUserById(user1.getId())
                    .ifPresent(user -> System.out.println("Found user: " + user));

            System.out.println("\n=== Updating user ===");
            user1.setEmail("john.new@example.com");
            User updatedUser = userService.updateUser(user1);
            System.out.println("Updated user: " + updatedUser);

            System.out.println("\n=== Checking email uniqueness ===");
            System.out.println("Is 'john.new@example.com' unique? " +
                    userService.isUserEmailUnique("john.new@example.com"));

            System.out.println("\n=== Deleting user ===");
            boolean isDeleted = userService.deleteUser(user2.getId());
            System.out.println("User deleted: " + isDeleted);

            System.out.println("\n=== Final users list ===");
            userService.getAllUsers().forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void initializeDatabase(Connection connection) throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL
                )
                """);
            System.out.println("Database initialized successfully");
        }
    }
}