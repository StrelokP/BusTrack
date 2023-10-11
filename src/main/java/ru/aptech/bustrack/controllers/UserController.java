package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.aptech.bustrack.services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
