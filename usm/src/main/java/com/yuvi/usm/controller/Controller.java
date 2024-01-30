package com.yuvi.usm.controller;

import com.yuvi.usm.model.User;
import com.yuvi.usm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @Autowired
  private UserService userService;

  @PostMapping(value="/saveUser")
  public Object saveCustomer(@RequestBody User user)
  {
    return (userService.createUser(user));
  }

  @GetMapping(value="/getUserById/{id}")
  public User getUserById(@PathVariable int id)throws NotFoundException
  {
    return (userService.getUserById(id));
  }

  @PutMapping(value="/updateUser")
  public Object updateUser(@RequestBody User user)
  {
    return (userService.updateUserById(user));
  }

  @DeleteMapping(value="deleteUser/{id}")
  public Object deleteUser(@PathVariable int id)
  {
    return (userService.deleteUser(id));
  }
}
