package com.example.demo.service;

import com.example.demo.beam.UserRequestParm;
import com.example.demo.beam.UserView;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

//    @Override
//    public User registerUser(User user) {
//        return userRepository.save(user);
//    }
@Override
public UserView createUser(UserRequestParm request) {
    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());
    userRepository.save(user);
    return new UserView(user);
}

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            return userRepository.save(existingUser);
        });
    }

    @Override
    public UserView upsertProfile(String keycloakUserId, UserRequestParm request, Jwt jwt) {
        User user = userRepository.findByKeycloakUserId(keycloakUserId)
                .orElse(new User());
        user.setKeycloakUserId(keycloakUserId);
        user.setName(request.getName() != null ? request.getName() : jwt.getClaimAsString("name"));
        user.setEmail(request.getEmail() != null ? request.getEmail() : jwt.getClaimAsString("email"));
        user.setPhone(request.getPhone());
        // Set other profile info if needed

        // Extract realm roles
        List<String> roles = jwt.getClaim("realm_access") != null ?
                (List<String>) ((Map<String, Object>) jwt.getClaims().get("realm_access")).get("roles")
                : null;

        // Pick only business roles
        Set<String> businessRoles = Set.of("hr", "developer", "candidate");
        String foundRole = null;
        if (roles != null) {
            for (String r : roles) {
                if (businessRoles.contains(r)) {
                    foundRole = r;
                    break;
                }
            }
        }

        user.setRoleName(foundRole); // will be null if not found
        userRepository.save(user);
        return new UserView(user);
    }

    @Override
    public UserView getByKeycloakUserId(String keycloakUserId) {
        return userRepository.findByKeycloakUserId(keycloakUserId)
                .map(UserView::new).orElse(null);
    }

    @Override
    public List<UserView> getAllUserViews() {
        return userRepository.findAll().stream()
                .map(UserView::new)
                .collect(Collectors.toList());
    }
}
