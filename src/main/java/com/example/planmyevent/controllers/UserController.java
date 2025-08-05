package com.example.planmyevent.controllers;

import com.example.planmyevent.dtos.RegisterDTO;
import com.example.planmyevent.exceptions.ResourceNotFoundException;
import com.example.planmyevent.models.Users;
import com.example.planmyevent.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServicesImpl;

    @GetMapping()
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userServicesImpl.getAllUsers();
        return new ResponseEntity<>(users, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            Users user = userServicesImpl.getUserById(id);
            return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        Users user = userServicesImpl.registerUser(registerDTO);
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }
}
