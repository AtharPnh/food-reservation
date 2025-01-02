package com.athar.food_reservation.web;

import com.athar.food_reservation.entities.User;
import com.athar.food_reservation.services.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String redirectToLogin(HttpServletRequest request) {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String showAccountUser(@RequestBody User user, HttpSession session, Model model) {
        log.info("in the post /login api");

        session.setAttribute("username", user.getUsername());
        session.setAttribute("roles", user.getRoles());

        // Pass user data to the account view
        model.addAttribute("username", user.getUsername());
        return "account";

//            model.addAttribute("error", "Invalid username or password");
//            return "login"; // Redirect back to login page

    }
}
