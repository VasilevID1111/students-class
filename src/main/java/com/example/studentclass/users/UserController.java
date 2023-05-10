package com.example.studentclass.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;


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
    @PostMapping("/users/create")
    public String registration(@RequestParam("login") String login,
                               @RequestParam("fio") String fio,
                               @RequestParam("email") String email,
                               @RequestParam("isActive") Boolean isActive,
                               RedirectAttributes redirectAttributes) {
        String password = userService.generatePassword(8);
        UserDTO user = new UserDTO();
        user.setLogin(login);
        user.setFio(fio);
        user.setEmail(email);
        user.setActive(isActive);
        System.out.println(password);
        user.setPassword(password);
        if (!userService.createUser(user)) {
            redirectAttributes.addAttribute("errorMessage", "Пользователь уже существует с логином " +
                    user.getLogin() + " уже существует под фио " + user.getFio());
        }
        return "redirect:/users";
    }

    @GetMapping("/logout")
    public String securityUrl() {
        return "redirect:/login";
    }
}
