package org.excercise.spring_la_mia_pizzeria_crud.controller;

import org.excercise.spring_la_mia_pizzeria_crud.model.Offerta;
import org.excercise.spring_la_mia_pizzeria_crud.model.Pizza;
import org.excercise.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzas = repository.findAll();

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = repository.findById(id).get();

        List<Offerta> offerte = pizza.getOfferte();

        Map<Integer, Integer> percentualiSconto = new HashMap<>();
        for(Offerta offerta : offerte) {
            int percentuale = offerta.getSconto().multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.HALF_UP).intValue();
            percentualiSconto.put(offerta.getId(), percentuale);
        }

        model.addAttribute("pizza", pizza);
        model.addAttribute("offerte", pizza.getOfferte());
        model.addAttribute("percentualiSconto", percentualiSconto);
        return "/pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }

        repository.save(formPizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }

        repository.save(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repository.deleteById(id);

        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/offerta")
    public String offerta(@PathVariable Integer id, Model model) {
        Offerta offerta = new Offerta();
        offerta.setPizza(repository.findById(id).get());
        model.addAttribute("offerta", offerta);
        return "offerte/create";
    }

    

}
