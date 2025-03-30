package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testUser");
        testUser.setEmail("test@example.com");
    }

    @Test
    void createUser_ShouldReturnSavedUser_WhenRepositorySucceeds() throws SQLException {
        when(repository.save(any(User.class))).thenReturn(testUser);

        User result = userService.createUser(testUser);

        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        verify(repository).save(testUser);
    }

    @Test
    void createUser_ShouldThrowDataAccessException_WhenRepositoryFails() throws SQLException {
        when(repository.save(any(User.class))).thenThrow(new SQLException("DB error"));

        DataAccessException exception = assertThrows(DataAccessException.class,
                () -> userService.createUser(testUser));

        assertEquals("Failed to save user", exception.getMessage());
        assertTrue(exception.getCause() instanceof SQLException);
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() throws SQLException {
        when(repository.findById(1L)).thenReturn(Optional.of(testUser));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals(testUser.getUsername(), result.get().getUsername());
        verify(repository).findById(1L);
    }

    @Test
    void getUserById_ShouldReturnEmpty_WhenUserNotExists() throws SQLException {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(99L);

        assertFalse(result.isPresent());
        verify(repository).findById(99L);
    }

    @Test
    void getUserById_ShouldThrowDataAccessException_WhenRepositoryFails() throws SQLException {
        when(repository.findById(anyLong())).thenThrow(new SQLException("DB error"));

        DataAccessException exception = assertThrows(DataAccessException.class,
                () -> userService.getUserById(1L));

        assertEquals("Failed to find user by id: 1", exception.getMessage());
    }

    @Test
    void getAllUsers_ShouldReturnUserList_WhenRepositorySucceeds() throws SQLException {
        List<User> users = List.of(testUser, new User(2L, "user2", "user2@test.com"));
        when(repository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void getAllUsers_ShouldThrowDataAccessException_WhenRepositoryFails() throws SQLException {
        when(repository.findAll()).thenThrow(new SQLException("DB error"));

        DataAccessException exception = assertThrows(DataAccessException.class,
                () -> userService.getAllUsers());

        assertEquals("Failed to find all users", exception.getMessage());
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser_WhenRepositorySucceeds() throws SQLException {
        when(repository.update(any(User.class))).thenReturn(testUser);

        User result = userService.updateUser(testUser);

        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        verify(repository).update(testUser);
    }

    @Test
    void updateUser_ShouldThrowDataAccessException_WhenRepositoryFails() throws SQLException {
        when(repository.update(any(User.class))).thenThrow(new SQLException("DB error"));

        DataAccessException exception = assertThrows(DataAccessException.class,
                () -> userService.updateUser(testUser));

        assertEquals("Failed to update user: 1", exception.getMessage());
    }

    @Test
    void deleteUser_ShouldReturnTrue_WhenDeletionSucceeds() throws SQLException {
        when(repository.delete(1L)).thenReturn(true);

        boolean result = userService.deleteUser(1L);

        assertTrue(result);
        verify(repository).delete(1L);
    }

    @Test
    void deleteUser_ShouldThrowDataAccessException_WhenRepositoryFails() throws SQLException {
        when(repository.delete(anyLong())).thenThrow(new SQLException("DB error"));

        DataAccessException exception = assertThrows(DataAccessException.class,
                () -> userService.deleteUser(1L));

        assertEquals("Failed to delete user: 1", exception.getMessage());
    }

    @Test
    void isUserEmailUnique_ShouldReturnTrue_WhenEmailIsUnique() throws SQLException {
        when(repository.findAll()).thenReturn(List.of(testUser));

          boolean result = userService.isUserEmailUnique("new@example.com");

        assertTrue(result);
        verify(repository).findAll();
    }

    @Test
    void isUserEmailUnique_ShouldReturnFalse_WhenEmailExists() throws SQLException {
        when(repository.findAll()).thenReturn(List.of(testUser));

        boolean result = userService.isUserEmailUnique("test@example.com");

        assertFalse(result);
    }

    @Test
    void isUserEmailUnique_ShouldThrowDataAccessException_WhenRepositoryFails() throws SQLException {
        when(repository.findAll()).thenThrow(new SQLException("DB error"));


        DataAccessException exception = assertThrows(DataAccessException.class,
                () -> userService.isUserEmailUnique("test@example.com"));

        assertEquals("Failed to check email uniqueness", exception.getMessage());
    }

    @Test
    void isUserEmailUnique_ShouldBeCaseInsensitive() throws SQLException {
        when(repository.findAll()).thenReturn(List.of(testUser));

        assertFalse(userService.isUserEmailUnique("TEST@example.com"));
        assertFalse(userService.isUserEmailUnique("test@EXAMPLE.COM"));
    }
}