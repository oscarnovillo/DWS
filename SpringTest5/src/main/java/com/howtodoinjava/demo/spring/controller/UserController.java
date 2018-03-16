package com.howtodoinjava.demo.spring.controller;

import daw.model.Cliente;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class UserController {

    @ResponseBody
    @RequestMapping("/")
    public String userForm(Model model) {
        //model.addAttribute("mensaje", "hola");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/dos")
    public String handleJson(@RequestParam Integer h,
      @RequestParam String jk, Model model) {

        return h + " " + jk;
    }
    @ModelAttribute("cliente")
    public Cliente formBackingObject() {
        return new Cliente();
    }
    
    @ResponseBody
    @RequestMapping(path="/dosobj",produces = MediaType.APPLICATION_JSON_VALUE)
    public Cliente handle(@ModelAttribute("cliente") Cliente cliente) {

        return cliente;
    }

    @RequestMapping(path="/j",produces = MediaType.APPLICATION_JSON_VALUE)
     public  @ResponseBody Cliente handleJson() {
        
         Cliente c = new Cliente();
         c.setName("hol");
         c.setSurname("jjj");
        return c;
    }
    
}
