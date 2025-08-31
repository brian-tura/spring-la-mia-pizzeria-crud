package org.excercise.spring_la_mia_pizzeria_crud.controller;

import org.excercise.spring_la_mia_pizzeria_crud.model.Offerta;
import org.excercise.spring_la_mia_pizzeria_crud.repository.OffertaRepository;
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
@RequestMapping("/offerte")
public class OffertaController {
    
    @Autowired
    private OffertaRepository repository;
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "offerte/create";
        }
        
        repository.save(formOfferta);
        return "redirect:/pizzas/" + formOfferta.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("offerta", repository.findById(id).get());
        return "offerte/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offerta")  Offerta formOfferta, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "offerte/edit";
        }

        repository.save(formOfferta);
        return "redirect:/pizzas/" + formOfferta.getPizza().getId();
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Offerta offerta = repository.findById(id).get();
        int pizzaId = offerta.getPizza().getId();
        repository.deleteById(id);

        return "redirect:/pizzas/" + pizzaId;
    }

}
