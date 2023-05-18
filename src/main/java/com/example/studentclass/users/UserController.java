package com.example.studentclass.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }
    @PostMapping("/users/create")
    public String userCreate(@RequestParam("login") String login,
                             @RequestParam("fio") String fio,
                             @RequestParam("email") String email,
                             @ModelAttribute("isActive") String isActive,
                             RedirectAttributes redirectAttributes) {
        String password = userService.generatePassword(8);
        UserDTO user = new UserDTO();
        user.setLogin(login);
        user.setFio(fio);
        user.setEmail(email);
        user.setActive(!isActive.isEmpty());
        System.out.println(password);
        user.setPassword(password);
        if (!userService.createUser(user)) {
            redirectAttributes.addFlashAttribute("message", "Пользователь уже существует с логином " +
                   user.getLogin() + " уже существует под ФИО " + userService.getUser(login).getFio());
        }
        return "redirect:/users";
    }
    @GetMapping("/users/deactivate/{user_id}")
    public String deactivateUser(@PathVariable Integer user_id) {
        userService.changeActivation(user_id);
        return "redirect:/users";
    }
    @GetMapping("/users/delete/{user_id}")
    public String deleteUser(@PathVariable Integer user_id) {
        userService.deleteUserById(user_id);
        return "redirect:/users";
    }
    @PostMapping("/user/check/login")
    public ResponseEntity<?> checkLoginUser(@RequestBody Map<String, String> requestData) {
        UserDTO user = userService.getUser(requestData.get("login"));
        if (user != null) {
            String message = "Пользователь: " + user.getFio();
            Integer userId = user.getId();
            String json = "{\"message\": \"" + message + "\", \"user_id\": \"" + userId + "\"}";
            return ResponseEntity.ok().body(json);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"" + "Пользователь не найден" + "\"}");
        }
    }
}
