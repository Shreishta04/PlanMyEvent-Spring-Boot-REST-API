package com.example.planmyevent.services;

import com.example.planmyevent.dtos.RegisterDTO;
import com.example.planmyevent.exceptions.ResourceNotFoundException;
import com.example.planmyevent.models.Users;
import com.example.planmyevent.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID: " + id + " not found."));
    }

    public Users registerUser(RegisterDTO userRegisterDTO) {
        Users user = new Users();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        return userRepository.save(user);
    }
}
