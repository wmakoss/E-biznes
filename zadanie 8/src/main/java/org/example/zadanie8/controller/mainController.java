package org.example.zadanie8.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.zadanie8.entity.User;
import org.example.zadanie8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Locale;

@Controller
public class mainController {

    @Autowired
    UserService userService;

    private void checkSession(Model model, String sessionCookie) {
        Integer userId = userService.checkSession(sessionCookie);
        System.out.println(userId);
        if (userId >= 0) {
            String username = userService.getUsernameById(userId);
            System.out.println(username);
            model.addAttribute("userId", userId);
            model.addAttribute("username", username);
        }
    }

    @GetMapping("/")
    public String index(Model model, @CookieValue(value = "session", defaultValue = "") String session) {
        checkSession(model, session);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model model, @CookieValue(value = "session", defaultValue = "") String session, HttpServletResponse response) {
        checkSession(model, session);

        Cookie cookie = new Cookie("session", "");
        response.addCookie(cookie);
        response.setStatus(303);
        response.addHeader("Location", "/");

        //TODO: Delete session from database

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, @CookieValue(value = "session", defaultValue = "") String session) {
        checkSession(model, session);
        return "login";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postLogin(Model model, @CookieValue(value = "session", defaultValue = "") String session, @RequestBody MultiValueMap<String, String> formData, HttpServletResponse response) {
        checkSession(model, session);

        String sessionUuid = userService.login(formData.toSingleValueMap().get("username"), formData.toSingleValueMap().get("password"));

        if (!sessionUuid.equals("")) {
            Cookie cookie = new Cookie("session", sessionUuid);
            response.addCookie(cookie);
            response.setStatus(303);
            response.addHeader("Location", "/");
        } else {
            model.addAttribute("error", "Nie poprawny login lub haslo");
        }

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, @CookieValue(value = "session", defaultValue = "") String session) {
        checkSession(model, session);
        return "register";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postRegister(Model model, @CookieValue(value = "session", defaultValue = "") String session, @RequestBody MultiValueMap<String, String> formData) {
        checkSession(model, session);

        String result = userService.addNewUser(formData.toSingleValueMap().get("username"), formData.toSingleValueMap().get("password"), formData.toSingleValueMap().get("password2"), formData.toSingleValueMap().get("mail"));

        System.out.println(result);

        if (result.equals("")) {
            return "welcome";
        } else {
            model.addAttribute("error", result);
            return "register";
        }
    }

    @GetMapping("/welcome")
    public String welcome(Model model, @CookieValue(value = "session", defaultValue = "") String session) {
        checkSession(model, session);
        return "welcome";
    }
}
