package com.example.demo.controller;

import com.example.demo.beam.UserRequestParm;
import com.example.demo.beam.UserView;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


@PostMapping("/add")
public ResponseEntity<UserView> createUser(@RequestBody UserRequestParm request) {
    UserView userView = userService.createUser(request);
    return ResponseEntity.ok(userView);
}

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    public ResponseEntity<UserView> getMyProfile(@AuthenticationPrincipal Jwt jwt) {
        String keycloakUserId = jwt.getSubject();
        UserView userView = userService.getByKeycloakUserId(keycloakUserId);
        if (userView == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userView);
    }

    // Add or update profile info (first login or update)
    @PostMapping("/me")
    public ResponseEntity<UserView> upsertMyProfile(@AuthenticationPrincipal Jwt jwt, @RequestBody UserRequestParm request) {
        String keycloakUserId = jwt.getSubject();
        UserView userView = userService.upsertProfile(keycloakUserId, request, jwt);
        return ResponseEntity.ok(userView);
    }

    // Only HRs: get all users


}
