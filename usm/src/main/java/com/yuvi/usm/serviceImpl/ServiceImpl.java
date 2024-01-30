package com.yuvi.usm.serviceImpl;

import jakarta.transaction.Transactional;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.yuvi.usm.model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.yuvi.usm.repository.UserRepository;
import com.yuvi.usm.service.UserService;

@Service
public class ServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;
  Map<String,Object> map= new HashMap<>();
  @Override
  @Transactional
  public Object createUser(User user) {
    userRepository.save(user);
    map.put("Status","Success");
    map.put("Msg","Data Saved Successfully");
    return map;
  }

  @Override
  public User getUserById(int id) throws NotFoundException {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> {
      Map<String, Object> errorDetails = new HashMap<>();
      errorDetails.put("message", "User not found with id: " + id);
      errorDetails.put("timestamp", new Date());
      errorDetails.put("status", HttpStatus.NOT_FOUND.value());
      return new NotFoundException();
    });
     }

  @Override
  public User updateUserById(User user) {
    return userRepository.saveAndFlush(user);
  }

  @Override
  public Object deleteUser(int id) {
    Map<String,Object> map= new HashMap<>();
    if(id==0){
      map.put("status","Error");
      map.put("msg","Invalid Id");
    }else {
      userRepository.deleteById(id);
      map.put("status","Success");
      map.put("msg","Data deleted successfully");
    }
    return map;
  }
}
