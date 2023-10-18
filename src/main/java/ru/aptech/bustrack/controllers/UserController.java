package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.aptech.bustrack.services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(@RequestParam(name = "name", required = false) String name) {
        return "index";
    }

    @PostMapping("/")
    public String postExample(@RequestParam(name = "name", required = false) String name) {
        return "index";
    }
}
