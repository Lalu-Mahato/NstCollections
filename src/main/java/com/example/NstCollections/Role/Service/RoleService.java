package com.example.NstCollections.Role.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.NstCollections.Role.Entity.Role;
import com.example.NstCollections.Role.Entity.Dto.CreateRoleDto;
import com.example.NstCollections.Role.Repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<Object> findAll() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    public ResponseEntity<Object> create(CreateRoleDto createRoleDto) {
        Optional<Role> existingRole = roleRepository.findRoleByName(createRoleDto.getName());
        if (existingRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Role already created");
        }

        Role role = new Role();
        role.setName(createRoleDto.getName());
        role.setDescription(createRoleDto.getDescription());

        Role response = roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
