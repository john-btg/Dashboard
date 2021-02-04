package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() {

        JdbcLogin nes = new JdbcLogin();
        nes.run();
        return "Greetings from Spring Boot!";
        //JdbcLogin jd = new JdbcLogin();
    }
}