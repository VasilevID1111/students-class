package com.example.studentclass.users;

import com.example.studentclass.computers.ComputerDTO;
import com.example.studentclass.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(UserDTO user) {
        if (userDAO.findByLogin(user.getLogin()) != null) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_STUDENT);
        userDAO.save(user);
        log.info("new user: {}", user.getFio());
        return true;
    }

    public UserDTO getUser(String login) {
        return userDAO.findByLogin(login);
    }

    public List<UserDTO> getUsers() {
        return userDAO.findAll();
    }

    public String generatePassword(Integer passwordLength) {
        String passwordChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()_+";
        return RandomStringUtils.random(passwordLength, passwordChars);
    }

    public Optional<UserDTO> getUserById(Integer user_id) {
        return userDAO.findById(user_id);
    }

    public boolean changeActivation(Integer userId) {
        Optional<UserDTO> user = getUserById(userId);
        if (user.isPresent()) {
            user.get().setActive(!user.get().isActive());
            userDAO.save(user.get());
            return true;
        } else {
            return false;
        }
    }

    public void deleteUserById(Integer userId) {
         userDAO.deleteById(userId) ;
    }
}
