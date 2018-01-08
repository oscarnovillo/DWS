package spring.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import spring.model.User;


@Controller
public class UserController {


	@GetMapping("/u")
	public String userForm(Locale locale, Model model) {
		model.addAttribute("mensaje", "hola");
		return "hello";
	}
	
}
