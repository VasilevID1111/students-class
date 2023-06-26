package com.example.studentclass.emails;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private String username;

    public void sendSimplePassword(String emailTo, String login, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(emailTo);
        message.setSubject("Пароль для учетной записи в studentclass");
        message.setText("Здравствуйте! Вас зарегистрировали в studentclass\n\n" +
                "Логин: " + login + "\n" +
                "Пароль: " + password);

        mailSender.send(message);
    }
}



