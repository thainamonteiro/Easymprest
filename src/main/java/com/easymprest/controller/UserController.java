package com.easymprest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.easymprest.model.Role;
import com.easymprest.model.User;
import com.easymprest.service.RoleService;
import com.easymprest.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;	
	
	@RequestMapping(value="/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();

		List<Role> roles = roleService.findAll();
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("cadastro");
		return modelAndView;
	}

	@RequestMapping(value="/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null && userExists.getActive() == 1) {
			bindingResult
					.rejectValue("email", "error.user",
							"Este email já foi registrado");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("cadastro");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Usuário registrado com sucesso!");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("cadastro");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		
		List<Role> roles = roleService.findAll();	
		modelAndView.addObject("roles", roles);		
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"Este email já foi registrado");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Usuário registrado com sucesso!");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

}
