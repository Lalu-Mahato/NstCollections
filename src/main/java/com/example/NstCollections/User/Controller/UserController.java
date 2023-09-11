package com.example.NstCollections.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NstCollections.User.Entity.Dto.CreateUserDto;
import com.example.NstCollections.User.Entity.Dto.UpdateUserDto;
import com.example.NstCollections.User.Service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> getUsers() {
        try {
            ResponseEntity<Object> response = userService.getUsers();
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody CreateUserDto createUserDto) {
        try {
            ResponseEntity<Object> response = userService.addUser(createUserDto);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try {
            ResponseEntity<Object> response = userService.deleteUserById(id);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id,
            @RequestBody UpdateUserDto updateUserDto) {
        try {
            ResponseEntity<Object> response = userService.updateUser(id, updateUserDto);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }
}
