package com.yuvi.usm.service;

import com.yuvi.usm.model.User;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface UserService {
public Object createUser(User user);
public User getUserById(int id) throws NotFoundException;
public Object updateUserById(User user);
public Object deleteUser(int id);
}
