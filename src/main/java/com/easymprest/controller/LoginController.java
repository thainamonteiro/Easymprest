package com.easymprest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.easymprest.model.User;
import com.easymprest.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();

		 if(SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
				 //when Anonymous Authentication is enabled
				 !(SecurityContextHolder.getContext().getAuthentication()
				          instanceof AnonymousAuthenticationToken)){

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User user = userService.findUserByEmail(auth.getName());
				modelAndView.addObject("userName", "Bem vindo " + user.getName() + " : " + user.getRegistration() + " (" + user.getEmail() + ")");
				modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuários logados");
				modelAndView.setViewName("index");


		 }else {

				modelAndView.setViewName("login");

		}

		return modelAndView;
	}




	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Bem vindo " + user.getName() + " : " + user.getRegistration() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuários logados");
		modelAndView.setViewName("index");
		return modelAndView;
	}

}