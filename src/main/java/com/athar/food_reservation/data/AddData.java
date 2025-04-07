package com.athar.food_reservation.data;

import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.role.Role;
import com.athar.food_reservation.security.Authority;
import com.athar.food_reservation.security.AuthorityRepository;
import com.athar.food_reservation.user.User;
import com.athar.food_reservation.role.RoleRepository;
import com.athar.food_reservation.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@EnableJpaAuditing
public class AddData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) {

        var menu_write = Authority.builder()
                .name("MENU_WRITE")
                .build();
        authorityRepository.save(menu_write);

        var reservation_write = Authority.builder()
                .name("RESERVATION_WRITE")
                .build();
        authorityRepository.save(reservation_write);

        var read = Authority.builder()
                .name("READ")
                .build();
        authorityRepository.save(read);

        var delete = Authority.builder()
                .name("DELETE")
                .build();
        authorityRepository.save(delete);

        var update = Authority.builder()
                .name("UPDATE")
                .build();
        authorityRepository.save(update);

        Role adminRole = Role.builder()
                .name("ADMIN")
                .authorities(Set.of(menu_write, reservation_write, read, delete, update))
                .build();
        roRepository.save(adminRole);

        Role employeeRole = Role.builder()
                .name("EMPLOYEE")
                .authorities(Set.of(read, reservation_write))
                .build();
        roRepository.save(employeeRole);

        Role staffRole = Role.builder()
                .name("STAFF")
                .authorities(Set.of(update, menu_write, reservation_write, read))
                .build();
        roRepository.save(staffRole);

        User athar = User.builder()
                .password(encoder.encode("1234"))
                .username("Athar")
                .phoneNumber("5036987452")
                .roles(Set.of(adminRole, employeeRole))
                .build();
        userRepository.save(athar);

        User bahador = User.builder()
                .password(encoder.encode("1234"))
                .username("Bahador")
                .phoneNumber("0257846914")
                .roles(Set.of(adminRole, staffRole))
                .build();
        userRepository.save(bahador);

        Meal meal = Meal.builder()
                .name("Pasta")
                .build();

//        User taha = User.builder()
//                .password(encoder.encode("1234"))
//                .username("TAHA")
//                .phoneNumber("0147895025")
//                .roles(Set.of(employeeRole))
//                .build();
//        userRepository.save(taha);

    }
}
