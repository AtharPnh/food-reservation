package com.athar.food_reservation.web;

import com.athar.food_reservation.entities.User;
import com.athar.food_reservation.services.HomeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String showAccountUser(@RequestBody User user, HttpSession session, Model model) {

        if(homeService.isValidUser(user)) {
            // Store user attributes in the session
            session.setAttribute("username", user.getUsername());
            session.setAttribute("roles", user.getRoles()); // Example: Store user roles

            // Pass user data to the account view
            model.addAttribute("username", user.getUsername());
            return "account";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Redirect back to login page
        }
    }
}
