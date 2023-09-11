package com.example.NstCollections.Role.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NstCollections.Role.Entity.Dto.CreateRoleDto;
import com.example.NstCollections.Role.Service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    @Autowired
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list-roles")
    public ResponseEntity<Object> findAll() {
        try {
            ResponseEntity<Object> response = roleService.findAll();
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }

    @PostMapping("/create-role")
    public ResponseEntity<Object> create(@RequestBody CreateRoleDto createUserDto) {
        try {
            ResponseEntity<Object> response = roleService.create(createUserDto);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }
}