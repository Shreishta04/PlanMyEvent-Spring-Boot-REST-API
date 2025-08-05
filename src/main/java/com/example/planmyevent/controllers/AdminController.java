package com.example.planmyevent.controllers;

import com.example.planmyevent.dtos.RegisterDTO;
import com.example.planmyevent.exceptions.ResourceNotFoundException;
import com.example.planmyevent.models.Admins;
import com.example.planmyevent.services.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminServicesImpl;

    @GetMapping()
    public ResponseEntity<List<Admins>> getAllAdmins() {
        List<Admins> admins = adminServicesImpl.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllAdmins(@PathVariable Long id) {
        try {
            Admins admin = adminServicesImpl.getAdminById(id);
            return new ResponseEntity<>(admin, HttpStatusCode.valueOf(200));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        Admins admin = adminServicesImpl.registerAdmin(registerDTO);
        return new ResponseEntity<>(admin, HttpStatusCode.valueOf(200));
    }
}
