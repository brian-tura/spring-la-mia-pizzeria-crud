package org.excercise.spring_la_mia_pizzeria_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @GetMapping
    public String index(Model model) {
        return "home";
    }
}
