package com.example.studentclass.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
//    @PostMapping("/registration")
//    public String registration(UserDTO user, Model model) {
//        if (!userService.createUser(user)) {
//            model.addAttribute("errorMessage", "Пользователь уже существует с логином " +
//                    user.getLogin() + " уже существует под фио " + user.getFio());
//            return "registration";
//        }
//        return "redirect:/login";
//    }
    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
}

    @GetMapping("/logout")
    public String securityUrl() {
        return "redirect:/login";
    }
}
