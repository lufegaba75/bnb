package com.lufegaba.bnb.controllers;

import com.lufegaba.bnb.domain.User;
import com.lufegaba.bnb.infraestructure.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createNewUser (@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> showAllUsers () {
        return ResponseEntity.ok(userService.showAllUsers());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<User> showUserById (@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteUserById (@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<User> updateUserById (@PathVariable Integer id, @RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
}
