package com.example.planmyevent.services;

import com.example.planmyevent.dtos.RegisterDTO;
import com.example.planmyevent.exceptions.ResourceNotFoundException;
import com.example.planmyevent.models.Admins;
import com.example.planmyevent.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminServices {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admins> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admins getAdminById(Long id) throws ResourceNotFoundException {
        return adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin with ID: " + id + " not found."));
    }

    public Admins registerAdmin(RegisterDTO registerDTO) {
        Admins admin = new Admins();
        admin.setUsername(registerDTO.getUsername());
        admin.setEmail(registerDTO.getEmail());
        admin.setPassword(registerDTO.getPassword());
        return adminRepository.save(admin);
    }
}
