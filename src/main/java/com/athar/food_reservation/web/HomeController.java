package com.athar.food_reservation.web;

import com.athar.food_reservation.auth.AuthenticationRequest;
import com.athar.food_reservation.auth.AuthenticationResponse;
import com.athar.food_reservation.auth.AuthenticationService;
import com.athar.food_reservation.services.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> showAccountUser(@RequestBody @Valid AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
