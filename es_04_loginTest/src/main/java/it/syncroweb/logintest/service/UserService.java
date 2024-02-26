package it.syncroweb.logintest.service;

import it.syncroweb.logintest.model.User;
import it.syncroweb.logintest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User insertUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user){
        User user1 = userRepository.findById(id).get();
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        return userRepository.save(user1);
    }

    public String deleteUser(Integer id, User user){
        userRepository.deleteById(id);
        return "User with id "+id+"is deleted from db";
    }
}*/
