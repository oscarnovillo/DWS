/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.controller;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/per")
public class Hello {


	@RequestMapping("/yo")
	public String userForm(@SessionAttribute(name = "test")String test,Locale locale, Model model) {
		model.addAttribute("mensaje", test);
                test = "yo";
		return "index";
	}
        
        
	@RequestMapping("/tu")
	public String testing(Locale locale, Model model) {
		model.addAttribute("mensaje", "hola");
		return "index";
	}
}
