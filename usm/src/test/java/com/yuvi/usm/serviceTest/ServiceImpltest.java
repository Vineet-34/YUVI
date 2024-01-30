package com.yuvi.usm.serviceTest;

import com.yuvi.usm.model.User;
import com.yuvi.usm.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
public class ServiceImpltest {

  @Mock
  private UserService userService;

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setFirstName("John");
    user.setLastName("John");
    user.setEmailId("johndoe@example.com");
    userService.createUser(user);
  }

  @Test
  @DisplayName("Test create user")
  void testCreateUser() {
    Map<String, Object> expected = new HashMap<>();
    expected.put("Status", "Success");
    expected.put("Msg", "Data Saved Successfully");
    Map<String, Object> actual = (Map<String, Object>) userService.createUser(user);
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test get user by id")
  void testGetUserById() throws NotFoundException {
    User actual = userService.getUserById(user.getId());
    assertEquals(user, actual);
  }

  @Test
  @DisplayName("Test get user by id not found")
  void testGetUserByIdNotFound() {
    assertThrows(NotFoundException.class, () -> userService.getUserById(999));
  }

  @Test
  @DisplayName("Test update user by id")
  void testUpdateUserById() {
    user.setFirstName("Jane Doe");
    User actual = (User) userService.updateUserById(user);
    assertEquals(user, actual);
  }

  @Test
  @DisplayName("Test delete user")
  void testDeleteUser() {
    Map<String, Object> expected = new HashMap<>();
    expected.put("status", "Success");
    expected.put("msg", "Data deleted successfully");
    Map<String, Object> actual = (Map<String, Object>) userService.deleteUser(user.getId());
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test delete user invalid id")
  void testDeleteUserInvalidId() {
    Map<String, Object> expected = new HashMap<>();
    expected.put("status", "Error");
    expected.put("msg", "Invalid Id");
    Map<String, Object> actual = (Map<String, Object>) userService.deleteUser(0);
    assertEquals(expected, actual);
  }

}
