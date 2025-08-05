package com.example.planmyevent.services;

import com.example.planmyevent.dtos.RegisterDTO;
import com.example.planmyevent.exceptions.ResourceNotFoundException;
import com.example.planmyevent.models.Admins;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServices {

    public List<Admins> getAllAdmins();

    public Admins getAdminById(Long id) throws ResourceNotFoundException;

    public Admins registerAdmin(RegisterDTO registerDTO);

}
