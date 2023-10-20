package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.aptech.bustrack.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aptech.bustrack.services.UserService;

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

    @PostMapping("/user")
    public ModelAndView createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            String login = userService.saveUser(user);
            modelAndView.setViewName("redirect:/welcome");
            redirectAttributes.addFlashAttribute("login", login);
        } catch (RuntimeException e) {
            modelAndView.setViewName("redirect:/");
            redirectAttributes.addFlashAttribute("err",
                    String.format("Ошибка при регистрации: %s", e.getMessage()));
        }
        return modelAndView;
    }
}
