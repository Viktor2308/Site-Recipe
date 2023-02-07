package me.sky.recipe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/")
    public String hello(){
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String info(){
        return "Victor Derkachev, " +
                "Site recipe, " +
                "01.02.2023, " +
                "Site recipe, Java, Spring Boot.";
    }
}