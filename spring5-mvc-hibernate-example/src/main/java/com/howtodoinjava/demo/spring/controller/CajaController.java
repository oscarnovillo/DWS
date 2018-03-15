package com.howtodoinjava.demo.spring.controller;

import com.howtodoinjava.demo.spring.model.Caja;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.howtodoinjava.demo.spring.model.User;
import com.howtodoinjava.demo.spring.service.CajaService;
import com.howtodoinjava.demo.spring.service.UserService;

@Controller
public class CajaController {

    @Autowired
    private CajaService userService;

    @GetMapping("/caja")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("cajas", userService.list());
        return "editCaja";
    }

    @ModelAttribute("caja")
    public Caja formBackingObject() {
        return new Caja();
    }

    @PostMapping("/addCaja")
    public String saveUser(@ModelAttribute("caja") @Valid Caja user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("cajas", userService.list());
            return "editCaja";
        }

        userService.save(user);
        return "redirect:/caja";
    }
}
