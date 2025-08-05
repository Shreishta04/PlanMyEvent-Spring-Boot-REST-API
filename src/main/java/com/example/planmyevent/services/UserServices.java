package com.example.planmyevent.services;

import com.example.planmyevent.dtos.RegisterDTO;
import com.example.planmyevent.exceptions.ResourceNotFoundException;
import com.example.planmyevent.models.Users;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserServices {
    public List<Users> getAllUsers();

    public Users getUserById(Long id) throws ResourceNotFoundException;

    public Users registerUser(RegisterDTO registerDTO);
}
