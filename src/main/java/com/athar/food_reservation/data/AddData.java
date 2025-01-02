package com.athar.food_reservation.data;

import com.athar.food_reservation.entities.Role;
import com.athar.food_reservation.entities.User;
import com.athar.food_reservation.repositories.RoleRepository;
import com.athar.food_reservation.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AddData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roRepository;

    @Override
    public void run(String... args) {
        Role adminRole = Role.builder()
                .createdAt(LocalDateTime.now())
                .name("ADMIN")
                .build();
        roRepository.save(adminRole);

        Role employeeRole = Role.builder()
                .createdAt(LocalDateTime.now())
                .name("EMPLOYEE")
                .build();
        roRepository.save(employeeRole);

        Role staffRole = Role.builder()
                .createdAt(LocalDateTime.now())
                .name("STAFF")
                .build();
        roRepository.save(staffRole);

        User athar = User.builder()
                .createdAt(LocalDateTime.now())
                .password(encoder.encode("1234"))
                .username("Athar")
                .phoneNumber("5036987452")
                .roles(Set.of(adminRole, employeeRole))
                .build();
        userRepository.save(athar);

        User bahador = User.builder()
                .createdAt(LocalDateTime.now())
                .password(encoder.encode("1234"))
                .username("Bahador")
                .phoneNumber("0257846914")
                .roles(Set.of(adminRole, staffRole))
                .build();
        userRepository.save(bahador);

        User taha = User.builder()
                .createdAt(LocalDateTime.now())
                .password(encoder.encode("1234"))
                .username("TAHA")
                .phoneNumber("0147895025")
                .roles(Set.of(employeeRole))
                .build();
        userRepository.save(taha);

    }
}
