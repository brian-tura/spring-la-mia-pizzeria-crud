package org.excercise.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.excercise.spring_la_mia_pizzeria_crud.model.Ingrediente;
import org.excercise.spring_la_mia_pizzeria_crud.model.Pizza;
import org.excercise.spring_la_mia_pizzeria_crud.repository.IngredienteRepository;
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

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {
    
    @Autowired
    private IngredienteRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Ingrediente> ingredienti = repository.findAll();
        model.addAttribute("ingredienti", ingredienti);
        return "ingredienti/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingrediente", new Ingrediente());
        return "ingredienti/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredienti/create";
        }

        repository.save(ingrediente);
        return "redirect:/ingredienti";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repository.deleteById(id);

        return "redirect:/ingredienti";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", repository.findById(id).get());
        return "ingredienti/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredienti/edit";
        }

        repository.save(ingrediente);
        return "redirect:/ingredienti";
    }
}
