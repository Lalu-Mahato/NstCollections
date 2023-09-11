package com.example.NstCollections.User.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.NstCollections.User.Entity.User;
import com.example.NstCollections.User.Entity.Dto.CreateUserDto;
import com.example.NstCollections.User.Entity.Dto.UpdateUserDto;
import com.example.NstCollections.User.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public ResponseEntity<Object> addUser(CreateUserDto createUserDto) {
        Optional<User> userByEmail = userRepository.findUserByEmail(createUserDto.getEmail());
        if (userByEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already taken");
        }

        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());

        User response = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Object> deleteUserById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    public ResponseEntity<Object> updateUser(Long id, UpdateUserDto updateUserDto) {
        Optional<User> data = userRepository.findById(id);
        if (!data.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = data.get();
        user.setName(updateUserDto.getName());
        User response = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
