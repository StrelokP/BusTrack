package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aptech.bustrack.services.UserService;

@SuppressWarnings("unused")
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }
}
