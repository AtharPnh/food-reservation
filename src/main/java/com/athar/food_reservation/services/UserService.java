package com.athar.food_reservation.services;

import com.athar.food_reservation.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("here");
        return userRepository.findByUsername(username)
                .map(user -> (UserDetails) user) // Explicitly cast User to UserDetails
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
