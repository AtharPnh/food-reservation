package com.athar.food_reservation.auth;

import com.athar.food_reservation.entities.Role;
import com.athar.food_reservation.entities.User;
import com.athar.food_reservation.jwt.JwtUtil;
import com.athar.food_reservation.repositories.RoleRepository;
import com.athar.food_reservation.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationResponse register(RegisterRequest request) {
        Set<Role> userRoles = request.getRoles().stream()
                .map(roleName -> roleRepository.findAllByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        var user = User.builder()
                .username(request.getUsername())
                .employeeId(request.getEmployeeId())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .accountLocked(false)
                .enabled(true)
                .roles(userRoles)
                .build();
        userRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = ((User) auth.getPrincipal());

        String token = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
