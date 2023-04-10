package com.example.studentclass.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository <UserDTO, Integer> {
    UserDTO findByLogin(String login);
}
