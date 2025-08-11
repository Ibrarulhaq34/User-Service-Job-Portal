package com.example.demo.service;

import com.example.demo.beam.UserRequestParm;
import com.example.demo.beam.UserView;
import com.example.demo.model.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    UserView createUser(UserRequestParm request);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> updateUser(Long id, User userDetails);

    UserView upsertProfile(String keycloakUserId, UserRequestParm request, Jwt jwt);
    UserView getByKeycloakUserId(String keycloakUserId);
    List<UserView> getAllUserViews();
}
